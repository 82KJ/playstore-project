package com.example.playstore.controller

import com.example.playstore.model.Account
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

    @GetMapping("")
    fun showUserPage(request:HttpServletRequest, model:Model): String {

        var account:Account = request.session.getAttribute("ss_account") as Account
        var id = account.id
        model.addAttribute("id",id)

        return "userMain.html"
    }

}