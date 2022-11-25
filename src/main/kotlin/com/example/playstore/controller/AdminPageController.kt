package com.example.playstore.controller

import com.example.playstore.model.Game
import com.example.playstore.service.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

@Controller
@RequestMapping("/admin")
class AdminPageController {

    @Autowired
    lateinit var gameService: GameService

    @GetMapping("")
    fun showAdminPage(model:Model, @RequestParam(required = false) search: String?): String {

        var games:List<Game>

        if (search == null){
            games = gameService.findVisibleGame()
            model.addAttribute("games", games)
        }
        else{
            games = gameService.findVisibleGame(search)
            model.addAttribute("games", games)
        }

        return "adminMain.html"
    }

    @GetMapping("/add")
    fun showAddGamePage():String{
        return "addGame.html"
    }

    @PostMapping("/add-game")
    fun addGame(game:Game, main_img:MultipartFile, sub_img:MultipartFile):String{
        var isAccept = gameService.saveGame(game, main_img, sub_img)

        return "redirect:/admin"
    }

    @GetMapping("/game/{gameId}")
    fun showGameModifyPage(@PathVariable gameId:Int, model: Model):String {
        var game = gameService.findGame(gameId)
        model.addAttribute("game", game)

        return "gameModify.html"
    }

    @PostMapping("/game/{gameId}/delete")
    fun deleteGame(@PathVariable gameId:Int): String{
        var isDelete = gameService.deleteGame(gameId)

        return "redirect:/admin"
    }

    @PostMapping("/game/{gameId}/modify")
    fun modifyGame(@PathVariable gameId: Int, game:Game, main_img:MultipartFile, sub_img:MultipartFile):String{

        gameService.modifyGame(game,main_img,sub_img,gameId)

        return "redirect:/admin"
    }

}