package com.example.playstore.controller

import com.example.playstore.model.Account
import com.example.playstore.model.Game
import com.example.playstore.service.AccountService
import com.example.playstore.service.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession


@Controller
@RequestMapping("/user/payment")
class PaymentPageController {

    @Autowired
    lateinit var gameService: GameService
    @Autowired
    lateinit var accountService: AccountService

    @PostMapping("")
    fun showPaymentPage(@RequestParam("checkedGame") gameIdList:List<String>, request: HttpServletRequest,model: Model): String {
        var session: HttpSession = request.session
        var account: Account = session.getAttribute("ss_account") as Account

        var gameList:MutableList<Game> = mutableListOf()
        var totalPaymentCost = 0
        gameIdList.forEach{
            var game = gameService.findGame(it.toInt())
            gameList.add(game)
            totalPaymentCost += game.price
        }

        model.addAttribute("games", gameList)
        model.addAttribute("account", account)
        model.addAttribute("totalPaymentCost", totalPaymentCost)
        model.addAttribute("remainingCost", account.gameMoney - totalPaymentCost)

        return "payment.html"
    }

    @PostMapping("/complete")
    fun showPaymentCompletePage(@RequestParam("gameIdList") gameIdList:List<String>, request: HttpServletRequest, model: Model): String {

        var session: HttpSession = request.session
        var account: Account = session.getAttribute("ss_account") as Account
        var totalPaymentCost = 0

        gameIdList.forEach{
            var game = gameService.findGame(it.toInt())
            totalPaymentCost += game.price
        }

        // 프론트앤드에서 판단하고 경고창 띄우기 --> 게임머니 충전 페이지로 이동하시겠습니까?
        if (totalPaymentCost > account.gameMoney){
            return "redirect:/user/mypage/charge"
        }

        // 1. account - game table insert 구현하기 + basket table 에서는 제거
        account.myGame = accountService.saveGameList(account.id, gameIdList)
        account.basket = accountService.deleteGameInBasket(account.id, gameIdList)
        account.gameMoney -= totalPaymentCost
        accountService.deductGameMoney(account.id, account.gameMoney)


        // 2.
        var gameList:MutableList<Game> = mutableListOf()
        gameIdList.forEach{
            var game = gameService.findGame(it.toInt())
            gameList.add(game)
        }
        model.addAttribute("games", gameList)
        model.addAttribute("totalPaymentCost", totalPaymentCost)

        return "paymentComplete.html"
    }


}