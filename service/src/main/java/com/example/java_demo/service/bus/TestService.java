package com.example.java_demo.service.bus;

import com.example.java_demo.service.interfaces.ITestService;

public class TestService implements ITestService {
    @Override
    public String getTestString() {
        return "Hello";
    }
}
