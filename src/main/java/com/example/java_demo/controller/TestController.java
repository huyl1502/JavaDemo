package com.example.java_demo.controller;

import com.example.java_demo.service.interfaces.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
    private final ITestService testService;

    public TestController(ITestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public String test() {
        return testService.getTestString();
    }
}
