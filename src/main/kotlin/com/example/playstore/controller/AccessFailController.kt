package com.example.playstore.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/accessFail")
class AccessFailController {

    @GetMapping("")
    fun showAccessFailPage(): String {
        return "accessFail.html"
    }

}