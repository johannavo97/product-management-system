package com.johanna.productmanagementproject.controllers;

import com.johanna.productmanagementproject.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller @Slf4j
public class HomeController {
    UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(value = {"/", "index"})
    public String homePage(Principal principal){
        if(principal != null) log.info(principal.getName());
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/403")
    public String accessDenied(){
        return "403";
    }
}
