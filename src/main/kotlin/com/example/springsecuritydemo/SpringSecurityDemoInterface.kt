package com.example.springsecuritydemo

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class SpringSecurityDemoRootController {

    @GetMapping
    fun get(authentication: Authentication?): DemoDTO {
        return DemoDTO(authentication?.name ?: "null")
    }
}

data class DemoDTO(
        val message: String
)
