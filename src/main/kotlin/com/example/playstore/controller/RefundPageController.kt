package com.example.playstore.controller

import com.example.playstore.model.Account
import com.example.playstore.model.Game
import com.example.playstore.service.AccountService
import com.example.playstore.service.GameService
import com.example.playstore.service.GameMoneyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/user/mypage/refund")
class RefundPageController {
    @Autowired
    lateinit var gameService: GameService
    @Autowired
    lateinit var gameMoneyService: GameMoneyService

    @GetMapping("")
    fun showRefundGameMoneyPage(request:HttpServletRequest, model:Model): String {
        var session: HttpSession = request.session
        var account:Account = session.getAttribute("ss_account") as Account
        println(account)

        var games:List<Game>

        games = listOf(gameService.findGame(9))
        model.addAttribute("games", games)
        model.addAttribute("gameMoney", account.gameMoney)

        // msg
        var msg = model.asMap()["msg"] as String?
        if (msg == null) msg = ""

        model.addAttribute("msg", msg)

        return "refundGameMoney.html"
    }
    @GetMapping("/modify")
    fun showRefundPage(): String {
        return "refund.html"
    }

    @PostMapping("")
    fun refundGameMoney(@RequestParam("refundMoney") refundMoney: Int,
                        redirectAttributes: RedirectAttributes,
                        request: HttpServletRequest,
                        model: Model):String{
        model.addAttribute("refundMoney", refundMoney)

        var session: HttpSession = request.session
        var account: Account = session.getAttribute("ss_account") as Account

        //사용자 게임 머니 = DB게임머니 - 환전금액 입력한 값
        var totalGameMoney = account.gameMoney - refundMoney

        return if (account.gameMoney > refundMoney) {
             gameMoneyService.modifyGameMoney(account.id, totalGameMoney)

            "redirect:/user/mypage/refund/modify"
        }
        else  {
            redirectAttributes.addFlashAttribute("msg", "현금화할 수 없는 금액입니다")
            "redirect:/user/mypage/refund"
        }
    }

}