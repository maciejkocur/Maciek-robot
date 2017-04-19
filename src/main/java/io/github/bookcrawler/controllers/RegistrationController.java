package io.github.bookcrawler.controllers;

import io.github.bookcrawler.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class RegistrationController {

    @GetMapping(value="/register")
    public String getRegistration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping(value="/register")
    public String getProfile(@ModelAttribute("user") User user){
        return "myProfile";
    }

}
