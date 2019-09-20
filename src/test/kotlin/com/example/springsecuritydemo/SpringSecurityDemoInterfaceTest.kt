package com.example.springsecuritydemo

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext


@RunWith(SpringRunner::class)
@SpringBootTest
class SpringSecurityDemoRootControllerTest {
    
    @Autowired
    lateinit var context: WebApplicationContext
    lateinit var mvc: MockMvc

    @Before
    fun before() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .build()
    }

    @Test
    @WithMockUser(username = "koo", roles = ["USER"], value = "Lee", password = "cool")
    fun root() {
        mvc.perform(get("/"))
                .andExpect(content().json(
                        "{\"message\":\"null\"}"
                ))
    }
}

@RunWith(SpringRunner::class)
@SpringBootTest
class SpringSecurityDemoLoginControllerTest {

    @Autowired
    lateinit var context: WebApplicationContext
    lateinit var mvc: MockMvc

    @Before
    fun before() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply<DefaultMockMvcBuilder>(springSecurity())
                .build()
    }

    @Test
    @WithMockUser(username = "koo")
    fun `GET login page`() {
        mvc.perform(get("/"))
                .andExpect(content().json(
                        "{\"message\":\"koo\"}"
                ))
    }
}


