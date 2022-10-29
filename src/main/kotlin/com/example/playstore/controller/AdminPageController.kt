package com.example.playstore.controller

import com.example.playstore.model.Game
import com.example.playstore.service.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin")
class AdminPageController {

    @Autowired
    lateinit var gameService: GameService

    @GetMapping("")
    fun showAdminPage(model:Model): String {

        var games:List<Game> = gameService.findAllGame()

        model.addAttribute("games", games)

        return "adminMain.html"
    }

}