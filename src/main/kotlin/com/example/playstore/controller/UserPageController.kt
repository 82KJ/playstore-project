package com.example.playstore.controller

import com.example.playstore.model.Account
import com.example.playstore.model.Game
import com.example.playstore.service.AccountService
import com.example.playstore.service.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/user")
class UserPageController {

    @Autowired
    lateinit var gameService: GameService
    @Autowired
    lateinit var accountService: AccountService

    @GetMapping("")
    fun showUserPage(request:HttpServletRequest, model:Model, @RequestParam(required = false) search: String?): String {
        var session: HttpSession = request.session
        var account:Account = session.getAttribute("ss_account") as Account

        println(account)

        var games:List<Game>

        if (search == null){
            games = gameService.findVisibleGame()
            model.addAttribute("games", games)
        }
        else{
            games = gameService.findVisibleGame(search)
            model.addAttribute("games", games)
        }

        model.addAttribute("gameMoney", account.gameMoney)

        return "userMain.html"
    }

    @GetMapping("/game/{gameId}")
    fun showGameInfoPage(@PathVariable gameId:Int, request:HttpServletRequest, model: Model):String {
        var game = gameService.findGame(gameId)

        var session: HttpSession = request.session
        var account:Account = session.getAttribute("ss_account") as Account

        var isInBasket = account.basket?.contains(gameId)
        var isInMyGame:Boolean = false
        account.myGame?.forEach {
            if(it.first == gameId){
                isInMyGame = true
                return@forEach
            }
        }

        var accountBirthDate = account.birthDate.toString()
        var day = accountBirthDate.substring(8,10).toInt()
        var month = accountBirthDate.substring(5,7).toInt()
        var year = accountBirthDate.substring(0,4).toInt()

        var now = LocalDate.now()
        var strNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        var accountAge = now.year - year
        if (now.monthValue - month <= 0 && now.dayOfMonth - day< 0){
            accountAge -= 1
        }

        var isLowerThanLimitAge = (accountAge < game.limit_age)

        model.addAttribute("isInBasket", isInBasket)
        model.addAttribute("isInMyGame", isInMyGame)
        model.addAttribute("isLowerThanLimitAge", isLowerThanLimitAge)
        model.addAttribute("game", game)

        return "gameInfo.html"
    }

    @PostMapping("/push-basket/{gameId}")
    fun pushGameIntoBasket(@PathVariable gameId:Int, request:HttpServletRequest, model:Model):String {

        var session: HttpSession = request.session
        var account:Account = session.getAttribute("ss_account") as Account
        var account_id = account.id

        var gameInBasket = accountService.saveBasket(account_id, gameId)
        account.basket = gameInBasket

        return "redirect:/user/game/${gameId}"
    }

}