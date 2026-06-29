package com.example.java_demo.controller;

import com.example.java_demo.service.interfaces.*;
import com.example.java_demo.config.HasPermission;
import com.example.java_demo.config.SecurityConstants;
import com.example.java_demo.service.bus.CacheFactory;
import com.example.java_demo.model.RolePermission;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    private final ITestService testService;
    private final CacheFactory cacheFactory;

    public TestController(ITestService testService, CacheFactory cacheFactory) {
        this.testService = testService;
        this.cacheFactory = cacheFactory;
    }

    @GetMapping
    public String test() {
        return testService.getTestString();
    }

    @GetMapping("admin-only")
    @HasPermission(feature = SecurityConstants.Feature.USER, function = SecurityConstants.Function.VIEW)
    @HasPermission(feature = SecurityConstants.Feature.MENU, function = SecurityConstants.Function.VIEW)
    public String adminOnly() {
        return "Access granted to user view or menu view endpoint!";
    }

    @PostMapping("set-role-permissions")
    public String setRolePermissions(@RequestParam String roleId, @RequestBody List<RolePermission> permissions) {
        String key = "role:permissions:" + roleId;
        cacheFactory.getCache().set(key, permissions, 3600);
        return "Permissions for role " + roleId + " set in Redis: " + permissions;
    }
}
