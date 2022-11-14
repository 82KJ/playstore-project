package com.example.playstore.controller

import com.example.playstore.model.Account
import com.example.playstore.model.Game
import com.example.playstore.service.AccountService
import com.example.playstore.service.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
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

        return "userMain.html"
    }

    @GetMapping("/game/{gameId}")
    fun showGameInfoPage(@PathVariable gameId:Int, request:HttpServletRequest, model: Model):String {
        var game = gameService.findGame(gameId)

        var session: HttpSession = request.session
        var account:Account = session.getAttribute("ss_account") as Account

        // account의 basket 안에 해당 게임 넘버가 있는지 찾기
        // 있다면, true 전달 --> 장바구니 추가 버튼 안보이기
        // 없다면, false 전달 --> 장바구니 추가 버튼 보이기
        if (account.basket?.contains(gameId) == true){
            print("장바구니에 존재")
            model.addAttribute("isInBasket", true)
            print(account)
        }
        else{
            print("장바구니에 없습니다")
            model.addAttribute("isInBasket", false)
            print(account)
        }

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

    @GetMapping("/mypage")
    fun showMyPage():String{
        return "mypage.html"
    }

    @GetMapping("/backet")
    fun showbacket():String{
        return "backet.html"
    }



}