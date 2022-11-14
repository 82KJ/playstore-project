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
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/signup")
class SignupPageController {

    @Autowired
    lateinit var accountService: AccountService

    @GetMapping("")
    fun showSignUpPage(model:Model, request:HttpServletRequest): String {
        // 세션 초기화 코드는 회원가입 페이지에서 필요없습니다~ 알아서 지워주세요!
//        var session: HttpSession = request.session
//        session.invalidate()

        // 하단 메세지 코드는 id 중복 체크때 msg 띄우는 용도로 활용하면 될수도?
        var msg = model.asMap()["msg"] as String?
        if (msg == null) msg = ""

        model.addAttribute("msg", msg)

        return "signup.html"
    }

    @PostMapping("/success")
    fun saveUserInfo(
        @RequestParam("id") id: String,
        @RequestParam("birthDate") birthDate: Date,
        @RequestParam("password") password: String,
        model: Model
    ): String {
        var account = Account(id = id, password = password, is_admin = 0, birthDate = birthDate, basket = null)
        accountService.saveAccount(account)

        model.addAttribute("id", id)
        model.addAttribute("password", password)

        return "signupSuccess.html"
    }
}