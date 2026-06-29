package com.example.java_demo.service.bus;

import com.example.java_demo.model.SystemConfig.RoleModel;
import com.example.java_demo.model.common.PagedRequest;
import com.example.java_demo.model.common.PagedResponse;
import com.example.java_demo.repository.interfaces.IRoleDAL;
import com.example.java_demo.service.interfaces.IRoleService;
import java.util.List;

public class RoleService implements IRoleService {
    private final IRoleDAL _roleDAL;

    public RoleService(IRoleDAL roleDAL) {
        _roleDAL = roleDAL;
    }

    @Override
    public boolean insert(RoleModel role) {
        return _roleDAL.insert(role);
    }

    @Override
    public boolean update(RoleModel role) {
        return _roleDAL.update(role);
    }

    @Override
    public boolean delete(String roleId) {
        return _roleDAL.delete(roleId);
    }

    @Override
    public RoleModel getById(String roleId) {
        return _roleDAL.getById(roleId);
    }

    @Override
    public List<RoleModel> getAll() {
        return _roleDAL.getAll();
    }

    @Override
    public PagedResponse<RoleModel> getAll(PagedRequest<?> request) {
        return _roleDAL.getAll(request);
    }
}
