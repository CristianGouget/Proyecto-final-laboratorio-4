package com.yt.backend.controller;

import com.yt.backend.model.User;
import com.yt.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import jakarta.validation.Valid;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hellousers")
    public String hello_world() {
        return "Hello World";
    }

    @PostMapping("/adduser")
    public String addUser(@RequestBody User user) {
        userService.addUser(user);
        return "User Added Successfully..";
    }

    @RequestMapping("/user/{userId}")
    public User getUserByuserId(@PathVariable("userId") long userId) {
        return userService.getUserByuserId(userId);
    }

    @RequestMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/user/{userId}")
    public String deleteUser(@PathVariable("userId") long userId) {
        userService.deleteUser(userId);
        return "User Deleted";
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        boolean isValidUser = userService.validateUser(user.getUsername(), user.getPassword());

        if (isValidUser) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }


}