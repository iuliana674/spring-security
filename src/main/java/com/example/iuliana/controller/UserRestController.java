package com.example.iuliana.controller;

import com.example.iuliana.model.User;
import com.example.iuliana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/users", produces = "application/json")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/user/{id}", produces = "application/json")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping(value = "/delete-user/{id}", produces = "application/json")
    public User delete(@PathVariable Long id){
        return userService.delete(id);
    }

    @PostMapping(value = "/save-user", produces = "application/json")
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @PostMapping(value = "/update-user/{id}", produces = "application/json")
    public User update(@PathVariable Long id, @RequestBody User user){
        return userService.update(id, user);
    }

}
