package com.example.playstore.controller


import com.example.playstore.model.Account
import com.example.playstore.model.Game
import com.example.playstore.service.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession


@Controller
@RequestMapping("/mypage")
class MyPageController {

    @GetMapping("")
    fun showMyPage(): String{
        return "mypage.html"
    }

}