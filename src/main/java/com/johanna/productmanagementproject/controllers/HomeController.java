package com.johanna.productmanagementproject.controllers;

import com.johanna.productmanagementproject.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@SessionAttributes(value = {"currentUser"})
@Slf4j
public class HomeController {
    UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/", "dashboard"})
    public String homePage(Principal principal, HttpSession session) {
        try {
            // HttpSession session = request.getSession(true);

            if (principal != null) {
                session.setAttribute("currentUser", userService.findByEmail(principal.getName()));
                log.info("session get attributes names: " + session.getAttributeNames().asIterator().toString());
                log.info("session ID: " + session.getId() + " Value of currentUser: " + session.getAttribute("currentUser").toString());
            }

        } catch (Exception e) {
            log.warn("homePage Exception!!");
            e.printStackTrace();
        }
        return "dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/contact")
    public String contact(HttpServletRequest request, Principal principal) {
        HttpSession session = request.getSession(true);
        log.info("session ID: " + session.getId() + " Value of currentUser: " + session.getAttribute("currentUser").toString());

        return "contact";
    }
}
