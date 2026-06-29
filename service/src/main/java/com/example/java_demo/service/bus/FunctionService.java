package com.example.java_demo.service.bus;

import com.example.java_demo.model.SystemConfig.FunctionModel;
import com.example.java_demo.model.common.PagedRequest;
import com.example.java_demo.model.common.PagedResponse;
import com.example.java_demo.repository.interfaces.IFunctionDAL;
import com.example.java_demo.service.interfaces.IFunctionService;
import java.util.*;

public class FunctionService implements IFunctionService {
    private final IFunctionDAL _functionDAL;

    public FunctionService(IFunctionDAL functionDAL) {
        _functionDAL = functionDAL;
    }

    @Override
    public boolean insert(FunctionModel function) {
        return _functionDAL.insert(function);
    }

    @Override
    public boolean update(FunctionModel function) {
        return _functionDAL.update(function);
    }

    @Override
    public boolean delete(String functionId) {
        return _functionDAL.delete(functionId);
    }

    @Override
    public FunctionModel getById(String functionId) {
        return _functionDAL.getById(functionId);
    }

    @Override
    public List<FunctionModel> getAll() {
        return _functionDAL.getAll();
    }

    @Override
    public PagedResponse<FunctionModel> getAll(PagedRequest<?> request) {
        return _functionDAL.getAll(request);
    }

    @Override
    public List<FunctionModel> getFunctionTree() {
        List<FunctionModel> allFunctions = _functionDAL.getAll();

        // Group function theo ParentFunctionId
        Map<String, List<FunctionModel>> groupByParent = new HashMap<>();
        for (FunctionModel function : allFunctions) {
            String parentId = function.getParentFunctionId() != null ? function.getParentFunctionId() : "";
            groupByParent.computeIfAbsent(parentId, k -> new ArrayList<>()).add(function);
        }

        // Gán children cho từng node, null nếu không có con
        for (FunctionModel function : allFunctions) {
            List<FunctionModel> children = groupByParent.get(function.getFunctionId());
            function.setChildren(children != null && !children.isEmpty() ? children : null);
        }

        // Trả về các node gốc (ParentFunctionId null hoặc rỗng)
        List<FunctionModel> rootFunctions = new ArrayList<>();
        for (FunctionModel function : allFunctions) {
            String parentId = function.getParentFunctionId();
            if (parentId == null || parentId.isEmpty()) {
                rootFunctions.add(function);
            }
        }
        return rootFunctions;
    }
}
