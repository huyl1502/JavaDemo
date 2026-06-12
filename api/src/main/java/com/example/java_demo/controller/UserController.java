package com.example.java_demo.controller;

import com.example.java_demo.model.*;
import com.example.java_demo.service.interfaces.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService _userService;

    public UserController(IUserService userService) {
        _userService = userService;
    }

    @PostMapping("InsertUser")
    public boolean insert(@RequestBody UserModel user) {
        return _userService.insert(user);
    }
}
