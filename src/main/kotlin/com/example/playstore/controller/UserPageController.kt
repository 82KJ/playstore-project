package com.example.playstore.controller

import com.example.playstore.model.Account
import com.example.playstore.model.Game
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
@RequestMapping("/user")
class UserPageController {

    @Autowired
    lateinit var gameService: GameService

    @GetMapping("")
    fun showUserPage(request:HttpServletRequest, model:Model, @RequestParam(required = false) search: String?): String {

        var games:List<Game>

        if (search == null){
            games = gameService.findVisibleGame()
            model.addAttribute("games", games)
        }
        else{
            games = gameService.findVisibleGame(search)
            model.addAttribute("games", games)
        }

        return "userMain.html"
    }

    @GetMapping("/mypage")
    fun showMyPage():String{
        return "mypage.html"
    }

    @GetMapping("/backet")
    fun showbacket():String{
        return "backet.html"
    }



}