package com.example.playstore.config

import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

@Component
class BootInterceptor: HandlerInterceptor {

    @Override
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        var session: HttpSession? = request.session

        if (session?.getAttribute("ss_id") == null) {
            response.sendRedirect("/")
            return false
        }

        return true
    }
}