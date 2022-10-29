package com.example.playstore.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig: WebMvcConfigurer {

    @Autowired
    lateinit var bootInterceptor: BootInterceptor
    @Autowired
    lateinit var adminInterceptor: AdminInterceptor

    @Override
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(bootInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/signup","/css/**", "/img/**", "/js/**")

        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/user/**")

    }

}