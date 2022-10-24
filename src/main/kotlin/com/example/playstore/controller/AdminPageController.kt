package com.example.playstore.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin")
class AdminPageController {

    @GetMapping("")
    fun showAdminPage(): String {
        return "adminMain.html"
    }
}