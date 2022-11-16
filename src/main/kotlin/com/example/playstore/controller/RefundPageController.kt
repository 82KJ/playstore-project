package com.example.playstore.controller

import com.example.playstore.model.Account
import com.example.playstore.model.Game
import com.example.playstore.service.AccountService
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
@RequestMapping("/refund")
class RefundPageController {
    @Autowired
    lateinit var gameService: GameService
    @Autowired
    lateinit var accountService: AccountService

    @GetMapping("")
    fun showMyPage(request:HttpServletRequest, model:Model): String {
        var session: HttpSession = request.session
        var account:Account = session.getAttribute("ss_account") as Account
        println(account)

        var games:List<Game>


        games = listOf(gameService.findGame(9))
        model.addAttribute("games", games)


        return "refund.html"
    }
}