package com.example.java_demo.service.interfaces;

import com.example.java_demo.model.SystemConfig.FunctionModel;
import com.example.java_demo.model.common.PagedRequest;
import com.example.java_demo.model.common.PagedResponse;
import java.util.List;

public interface IFunctionService {
    boolean insert(FunctionModel function);
    boolean update(FunctionModel function);
    boolean delete(String functionId);
    FunctionModel getById(String functionId);
    List<FunctionModel> getAll();
    PagedResponse<FunctionModel> getAll(PagedRequest<?> request);
}
