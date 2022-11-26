package com.example.playstore.controller


import com.example.playstore.model.Account
import com.example.playstore.model.Game
import com.example.playstore.service.AccountService
import com.example.playstore.service.GameMoneyService
import com.example.playstore.service.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession


@Controller
@RequestMapping("/user/mypage")
class MyPageController {

    @Autowired
    lateinit var gameService: GameService
    @Autowired
    lateinit var gameMoneyService : GameMoneyService
    @Autowired
    lateinit var accountService : AccountService

    @GetMapping("")
    fun showMyPage(request:HttpServletRequest, model:Model): String {
        var session: HttpSession = request.session
        var account:Account = session.getAttribute("ss_account") as Account
        var myGames:MutableList<Game> = mutableListOf()
        var refundable=mutableListOf<Int>()

        account.myGame = accountService.findMyGame(account.id)
        account.myGame?.forEach {
            myGames.add(gameService.findGame(it.first))
        }

        val comparator:Comparator<Game> = compareBy {it.invisible}
        myGames.sortWith(comparator)

        val count=account.myGame?.size

        for(i in 1..count!!){
            var rTime=account.myGame?.get(i-1)?.second
            if(rTime==null){
                refundable.add(1)
            }
            else{
                rTime=rTime.plusHours(2)
                var n:LocalDateTime=LocalDateTime.now()
                if(n<=rTime){
                    refundable.add(1)
                }
                else{
                    refundable.add(0)
                }
            }
        }

        model.addAttribute("games", myGames)
        model.addAttribute("gameMoney", account.gameMoney)
        model.addAttribute("refundable", refundable)

        return "mypage.html"
    }

    @GetMapping("/charge")
    fun showChargePage(): String {

        return "addGamemoney.html"
    }

    @PostMapping("/add-money")
    fun chargeMoney( @RequestParam("addMoney") addMoney: String, request:HttpServletRequest, model:Model) : String{

        var session: HttpSession = request.session
        var account:Account = session.getAttribute("ss_account") as Account

        model.addAttribute("addMoney", addMoney)

        var totalGameMoney = 0

        if (addMoney == "5000") {
            totalGameMoney = account.gameMoney + 5000
        }
        else if (addMoney == "10000") {
            totalGameMoney = account.gameMoney + 10000
        }
        else if (addMoney == "50000") {
            totalGameMoney = account.gameMoney + 50000
        }
        else {
            totalGameMoney = account.gameMoney + 1000
        }

        account.gameMoney = totalGameMoney
        gameMoneyService.modifyGameMoney(account.id,totalGameMoney)

        model.addAttribute("addMoney", addMoney)

        return "addGamemoneyComplete.html"
    }

    @GetMapping("/playGame/{gameId}")
    fun playGame(@PathVariable gameId:Int, request:HttpServletRequest, model:Model): String {
        var session: HttpSession = request.session
        var account: Account = session.getAttribute("ss_account") as Account
        var account_id = account.id
        var indexNum = 0

        while(true){
            if(account.myGame?.get(indexNum)?.first==gameId){
                break
            }
            indexNum++
        }
        var playTime = account.myGame?.get(indexNum)?.second

        if(playTime==null){
            accountService.setPlayTime(account_id, gameId, LocalDateTime.now())
            account.myGame = accountService.findMyGame(account.id)
        }

        return "redirect:/user/mypage"
    }

    @GetMapping("/refundGame/{gameId}")
    fun refundGame(@PathVariable gameId:Int, request:HttpServletRequest, model:Model): String {
        var session: HttpSession = request.session
        var account:Account = session.getAttribute("ss_account") as Account

        // 1. 게임 삭제 요청
        account.myGame = accountService.deleteGameList(account.id, gameId)

        var game = gameService.findGame(gameId)

        // 2. 게임 머니 환불 요청
        var PreviousCost = account.gameMoney
        account.gameMoney += game.price
        gameMoneyService.modifyGameMoney(account.id, account.gameMoney)

        model.addAttribute("PreviousCost", PreviousCost)
        model.addAttribute("gameName", game.name)
        model.addAttribute("gamePrice", game.price)
        model.addAttribute("remainingCost", account.gameMoney)

        return "gameRefundComplete.html"
    }
}