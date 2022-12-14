package com.example.playstore.controller


import com.example.playstore.model.Account
import com.example.playstore.model.Game
import com.example.playstore.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.sql.Date
import java.time.LocalDate
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/signup")
class SignupPageController {

    @Autowired
    lateinit var accountService: AccountService

    @GetMapping("")
    fun showSignUpPage(model: Model, request: HttpServletRequest): String {
        // id 중복 체크 msg
        var msg = model.asMap()["msg"] as String?
        if (msg == null) msg = ""

        model.addAttribute("msg", msg)
        model.addAttribute("maxDate", LocalDate.now())

        return "signup.html"
    }

    @PostMapping("")
    fun saveUserInfo(
        @RequestParam("id") id: String,
        @RequestParam("birthDate") birthDate: Date,
        @RequestParam("password") password: String,
        @RequestParam("pwcheck") pwcheck: String,
        redirectAttributes: RedirectAttributes,
        request: HttpServletRequest,
        model: Model
    ): String {
        var idcheck = accountService.findAccount(id)

        return if (idcheck == null) {
            var account = Account(id = id, password = password, is_admin = 0, birthDate = birthDate, gameMoney = 0)
            accountService.saveAccount(account)
            model.addAttribute("id", id)
            "signupSuccess.html"
        } else {
            redirectAttributes.addFlashAttribute("id", id)
            redirectAttributes.addFlashAttribute("date", birthDate)
            redirectAttributes.addFlashAttribute("pw", password)
            redirectAttributes.addFlashAttribute("pwcheck", pwcheck)
            redirectAttributes.addFlashAttribute("msg", "이미 존재하는 ID입니다")
            "redirect:/signup"
        }
    }
}