package com.example.playstore.config

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

        var isAdmin:Any? = session?.getAttribute("ss_is_admin")

        if (isAdmin == null || isAdmin == 0) {
            response.sendRedirect("/accessFail")
            return false
        }

        return true
    }

}