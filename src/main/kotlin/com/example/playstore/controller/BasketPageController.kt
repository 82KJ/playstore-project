package com.example.playstore.controller

import com.example.playstore.model.Account
import com.example.playstore.model.Game
import com.example.playstore.service.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/user/basket")
class BasketPageController {

    @Autowired
    lateinit var gameService: GameService

    @GetMapping("")
    fun showBasketPage(request: HttpServletRequest, model: Model): String {
        var session: HttpSession = request.session
        var account: Account = session.getAttribute("ss_account") as Account

        var myBasketGames:MutableList<Game> = mutableListOf()

        account.basket?.forEach {
            myBasketGames.add(gameService.findGame(it))
        }

        //print(myBasketGames)

        model.addAttribute("games", myBasketGames)
        model.addAttribute("account", account)

        return "basket.html"
    }
}