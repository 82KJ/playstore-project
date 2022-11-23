package com.example.playstore.controller


import com.example.playstore.model.Account
import com.example.playstore.model.Game
import com.example.playstore.service.AccountService
import com.example.playstore.service.GameMoneyService
import com.example.playstore.service.GameService
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
@RequestMapping("")
class resetPasswordController {

    @Autowired
    lateinit var accountService: AccountService
    @Autowired
    lateinit var gameService: GameService
    @Autowired
    lateinit var gameMoneyService:GameMoneyService


    @GetMapping("/user/mypage/resetPassword")
    fun showResetPassword(request:HttpServletRequest, model:Model):String {
        var session: HttpSession = request.session
        var account:Account = session.getAttribute("ss_account") as Account
        return "resetPassword.html"
    }

    @PostMapping("/user/mypage/resetPassword/reset-Password")
    fun resetPassword(@RequestParam("password")pw:String, @RequestParam("passwordCheck")pck:String, redirectAttributes: RedirectAttributes, request:HttpServletRequest, model:Model):String{
        var session: HttpSession = request.session
        var account:Account = session.getAttribute("ss_account") as Account
        var id=account.id


        if(pw==pck){
            var account = accountService.resetPassword(id,pw)
        }
        return "redirect:/user/mypage"
    }


}