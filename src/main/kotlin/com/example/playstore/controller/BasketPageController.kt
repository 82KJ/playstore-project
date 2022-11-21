package com.example.playstore.controller

import com.example.playstore.model.Account
import com.example.playstore.model.Game
import com.example.playstore.service.AccountService
import com.example.playstore.service.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/user/basket")
class BasketPageController {

    @Autowired
    lateinit var gameService: GameService
    @Autowired
    lateinit var accountService: AccountService

    @GetMapping("")
    fun showBasketPage(request: HttpServletRequest, model: Model): String {
        var session: HttpSession = request.session
        var account: Account = session.getAttribute("ss_account") as Account

        var myBasketGames:MutableList<Game> = mutableListOf()

        account.basket?.forEach {
            myBasketGames.add(gameService.findGame(it))
        }


        model.addAttribute("games", myBasketGames)
        model.addAttribute("account", account)

        return "basket.html"
    }

    @GetMapping("/delete/{gameId}")
    fun deleteGameInBasket(@PathVariable gameId:Int,request: HttpServletRequest, model: Model): String {
        var session: HttpSession = request.session
        var account: Account = session.getAttribute("ss_account") as Account

        var baskets = accountService.deleteGameInBasket(account.id, gameId)
        account.basket = baskets

        return "redirect:/user/basket"
    }
}