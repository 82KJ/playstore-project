package com.example.playstore.controller

import com.example.playstore.mapper.UserMapper
import com.example.playstore.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/signup")
class SignupPageController {

    @Autowired
    lateinit var userMapper:UserMapper

    @GetMapping("")
    fun showSignUpPage(): String {
        return "signup.html"
    }

    @PostMapping("/success")
    fun saveUserInfo(@RequestParam("id") id:String, @RequestParam("password") password:String, model:Model): String {
        var user = User(id=id, password=password)

        model.addAttribute("id", id)
        model.addAttribute("password", password)

        userMapper.insert(user)
        return "signupSuccess.html"
    }

}