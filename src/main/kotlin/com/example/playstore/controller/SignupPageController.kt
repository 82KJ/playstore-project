package com.example.playstore.controller


import com.example.playstore.model.Account
import com.example.playstore.service.AccountService
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
    lateinit var accountService: AccountService

    @GetMapping("")
    fun showSignUpPage(): String {
        return "signup.html"
    }

    @PostMapping("/success")
    fun saveUserInfo(@RequestParam("id") id:String, @RequestParam("password") password:String, model:Model): String {
        var account = Account(id=id, password=password, is_admin = 0)
        accountService.saveAccount(account)

        model.addAttribute("id", id)
        model.addAttribute("password", password)

        return "signupSuccess.html"
    }

}