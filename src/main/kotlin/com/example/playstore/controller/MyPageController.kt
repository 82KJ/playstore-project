package com.example.playstore.controller


import com.example.playstore.model.Account
import com.example.playstore.model.Game
import com.example.playstore.service.AccountService
import com.example.playstore.service.GameMoneyService
import com.example.playstore.service.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession


@Controller
@RequestMapping("/user/mypage")
class MyPageController {

    @Autowired
    lateinit var gameService: GameService
    @Autowired
    lateinit var gameMoneyService : GameMoneyService

    @GetMapping("")
    fun showMyPage(request:HttpServletRequest, model:Model): String {
        var session: HttpSession = request.session
        var account:Account = session.getAttribute("ss_account") as Account
        var myGames:MutableList<Game> = mutableListOf()

        account.myGame?.forEach {
            myGames.add(gameService.findGame(it.first))
        }

        val comparator:Comparator<Game> = compareBy {it.invisible}
        myGames.sortWith(comparator)

        model.addAttribute("games", myGames)
        model.addAttribute("gameMoney", account.gameMoney)

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

        gameMoneyService.modifyGameMoney(account.id,totalGameMoney)

        return "redirect:/user/mypage/charge"
    }
}