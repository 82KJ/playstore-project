package com.example.playstore.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/")
class LoginPageController {

    @GetMapping("")
    fun showLoginPage(): String {
        return "login.html"
    }

    @PostMapping("")
    fun checkLogin(): String{
        // 1. db에서 select해서 찾기

        // 2. password가 일치하는지 확인하기

        // 3. 성공하면 --> 관리자 , 사용자 여부에 따라 다른 page 반환
        return "redirect:/user"
        // 4. 실패하면 --> 실패했다는 page 보여주기

    }
}