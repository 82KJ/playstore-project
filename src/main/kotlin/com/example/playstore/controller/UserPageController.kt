package com.example.playstore.controller

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

        var id = request.session.getAttribute("ss_id")
        model.addAttribute("id",id)

        return "userMain.html"
    }

}