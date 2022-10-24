package com.example.playstore.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/user")
class UserPageController {

    @GetMapping("")
    fun showUserPage(): String {
        return "userMain.html"
    }
}