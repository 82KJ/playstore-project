package com.example.playstore.config

import com.example.playstore.model.Account
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

@Component
class AdminInterceptor: HandlerInterceptor {

    @Override
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        var session: HttpSession? = request.session

        var account:Account? = session?.getAttribute("ss_account") as Account
        var isAdmin = account?.is_admin

        if (isAdmin == null || isAdmin == 0) {
            response.sendRedirect("/accessFail")
            return false
        }

        return true
    }

}