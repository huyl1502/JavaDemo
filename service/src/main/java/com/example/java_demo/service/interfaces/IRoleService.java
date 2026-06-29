package com.example.java_demo.service.interfaces;

import com.example.java_demo.model.SystemConfig.RoleModel;
import com.example.java_demo.model.common.PagedRequest;
import com.example.java_demo.model.common.PagedResponse;
import java.util.List;

public interface IRoleService {
    boolean insert(RoleModel role);
    boolean update(RoleModel role);
    boolean delete(String roleId);
    RoleModel getById(String roleId);
    List<RoleModel> getAll();
    PagedResponse<RoleModel> getAll(PagedRequest<?> request);
}
