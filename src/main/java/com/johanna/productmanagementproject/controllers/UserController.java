package com.johanna.productmanagementproject.controllers;

import com.johanna.productmanagementproject.models.User;
import com.johanna.productmanagementproject.services.ProductService;
import com.johanna.productmanagementproject.services.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller @Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(value = "users")
public class UserController {
    UserService userService;
    ProductService productService;

    @Autowired
    public UserController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/users")
    public String getAllStudents(Model model, Principal principal){

        log.info(principal.getName());
        model.addAttribute("users",userService.findAll());

        return "users";
    }

    @GetMapping("/users/new")
    public String createStudentForm(Model model) {

        // create user object to hold user form data
        User user = new User();
        model.addAttribute("user", user);
        return "create_user";

    }

    @PostMapping("/users")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveOrUpdate(user);
        return "redirect:/students";
    }

    @PostMapping("/users/{email}")
    public String updateUser(@PathVariable String email,
                                @ModelAttribute("user") User user,
                                Model model) {

        // get student from database by id
        User existingUser = userService.findByEmail(email);
        existingUser.setEmail(user.getEmail());
        existingUser.setName(user.getName());
        existingUser.setPassword(user.getPassword());
        existingUser.setContactNumber(user.getContactNumber());

        // save updated student object
        userService.saveOrUpdate(existingUser);
        return "redirect:/users";
    }

    // handler method to handle delete User request

    @GetMapping("/users/{email}")
    public String deleteUser(@PathVariable String email) {
        userService.deleteUserByEmail(email);
        return "redirect:/users";
    }
}
