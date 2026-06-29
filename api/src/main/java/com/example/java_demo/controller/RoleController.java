package com.example.java_demo.controller;

import com.example.java_demo.model.SystemConfig.RoleModel;
import com.example.java_demo.service.interfaces.IRoleService;
import com.example.java_demo.model.common.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final IRoleService _roleService;

    public RoleController(IRoleService roleService) {
        _roleService = roleService;
    }

    @PostMapping("InsertRole")
    public boolean insert(@RequestBody RoleModel role) {
        return _roleService.insert(role);
    }

    @PutMapping("UpdateRole")
    public boolean update(@RequestBody RoleModel role) {
        return _roleService.update(role);
    }

    @DeleteMapping("DeleteRole/{roleId}")
    public boolean delete(@PathVariable String roleId) {
        return _roleService.delete(roleId);
    }

    @GetMapping("GetRoleById/{roleId}")
    public RoleModel getById(@PathVariable String roleId) {
        return _roleService.getById(roleId);
    }

    @GetMapping("GetAllRoles")
    public List<RoleModel> getAll() {
        return _roleService.getAll();
    }

    @GetMapping("GetRolesPaged")
    public PagedResponse<RoleModel> getRolesPaged(PagedRequest<?> request) {
        return _roleService.getAll(request);
    }
}
