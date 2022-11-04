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
import org.springframework.web.servlet.support.RequestContextUtils
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession


@Controller
@RequestMapping("/")
class LoginPageController {

    @Autowired
    lateinit var accountService: AccountService

    @GetMapping("")
    fun showLoginPage(model:Model): String {
        var msg = model.asMap()["msg"] as String?
        if (msg == null) msg = ""

        model.addAttribute("msg", msg)
        return "login.html"
    }

    @PostMapping("")
    fun checkLogin(@RequestParam("id") id:String, @RequestParam("password") password:String, request:HttpServletRequest, redirectAttributes: RedirectAttributes): String {
        var account = accountService.findAccount(id)

        return if (account == null){
            redirectAttributes.addFlashAttribute("msg", "ID가 존재하지 않습니다")
            "redirect:/"
        } else if (account.password != password){
            redirectAttributes.addFlashAttribute("msg", "Password가 일치하지 않습니다")
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