package com.example.playstore.controller

import com.example.playstore.service.AccountService
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
@RequestMapping("/")
class LoginPageController {

    @Autowired
    lateinit var accountService: AccountService

    @GetMapping("")
    fun showLoginPage(): String {
        return "login.html"
    }

    @PostMapping("")
    fun checkLogin(@RequestParam("id") id:String, @RequestParam("password") password:String, request:HttpServletRequest): String {
        var account = accountService.findAccount(id, password)

        return if (account == null){
            "redirect:/"
        } else if (account.is_admin == 1){
            var session:HttpSession = request.session
            session.setAttribute("ss_account", account)
            "redirect:/admin"
        } else{
            var session:HttpSession = request.session
            session.setAttribute("ss_account", account)
            "redirect:/user"
        }
    }
}