package com.example.playstore.controller


import com.example.playstore.model.Account
import com.example.playstore.model.Game
import com.example.playstore.service.AccountService
import com.example.playstore.service.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.expression.spel.ast.NullLiteral
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
    lateinit var accountService: AccountService

    @GetMapping("")
    fun showMyPage(request:HttpServletRequest, model:Model): String {
        var session: HttpSession = request.session
        var account:Account = session.getAttribute("ss_account") as Account
        var myGames:MutableList<Game> = mutableListOf()

        account.myGame?.forEach {
            myGames.add(gameService.findGame(it.first))
        }

        model.addAttribute("games", myGames)
        model.addAttribute("gameMoney", account.gameMoney)
        return "mypage.html"
    }

    @GetMapping("/charge")
    fun showChargePage(request:HttpServletRequest, model:Model): String {

        return "addGamemoney.html"
    }

    @GetMapping("/refundmoney")
    fun showRefundPage(request:HttpServletRequest, model:Model): String {

        return "refundGamemoney.html"
    }

    @GetMapping("/playGame/{gameId}")
    fun playGame(@PathVariable gameId:Int,  request:HttpServletRequest, model:Model): String {
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

        print(playTime)
        if(playTime==null){
            accountService.setPlayTime(account_id, gameId, LocalDateTime.now())
        }

        return "playGame.html"


    }

}