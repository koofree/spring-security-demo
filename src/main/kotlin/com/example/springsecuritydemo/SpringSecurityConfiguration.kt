package com.example.springsecuritydemo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
class SpringSecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Bean
    override fun userDetailsService(): UserDetailsService {
        val service = InMemoryUserDetailsManager()
        service.createUser(User.builder()
                .username("user")
                .password("password")
                .passwordEncoder(passwordEncoder::encode)
                .roles("USER")
                .build())
        return service
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
