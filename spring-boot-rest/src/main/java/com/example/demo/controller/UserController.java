package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("User")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/add")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.removeById(id);
    }

    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.findById(id);
    }

}
