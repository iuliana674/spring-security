package com.example.iuliana.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = "users")
    public String getUsers(Model model){
        return "usersPage";
    }

}
