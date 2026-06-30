package com.example.java_demo.service.bus;

import com.example.java_demo.model.SystemConfig.FunctionModel;
import com.example.java_demo.model.common.PagedRequest;
import com.example.java_demo.model.common.PagedResponse;
import com.example.java_demo.repository.interfaces.IFunctionDAL;
import com.example.java_demo.service.interfaces.IFunctionService;
import java.util.List;

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
}
