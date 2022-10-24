package com.example.playstore.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/admin")
class AdminPageController {

    @GetMapping("")
    fun showAdminPage(request:HttpServletRequest): String {

        return "adminMain.html"
    }

}