package com.example.iuliana.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = "/")
    public String getHomePage(Model model){
        return "homePage";
    }

    @GetMapping(value = "/users")
    public String getUsers(Model model){
        return "usersPage";
    }

    @GetMapping(value = "/admin")
    public String getAdminPage(Model model){
        return "adminPage";
    }

}
