package com.example.java_demo.controller;

import com.example.java_demo.model.SystemConfig.FunctionModel;
import com.example.java_demo.service.interfaces.IFunctionService;
import com.example.java_demo.model.common.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/function")
public class FunctionController {
    private final IFunctionService _functionService;

    public FunctionController(IFunctionService functionService) {
        _functionService = functionService;
    }

    @PostMapping("InsertFunction")
    public boolean insert(@RequestBody FunctionModel function) {
        return _functionService.insert(function);
    }

    @PutMapping("UpdateFunction")
    public boolean update(@RequestBody FunctionModel function) {
        return _functionService.update(function);
    }

    @DeleteMapping("DeleteFunction/{functionId}")
    public boolean delete(@PathVariable String functionId) {
        return _functionService.delete(functionId);
    }

    @GetMapping("GetFunctionById/{functionId}")
    public FunctionModel getById(@PathVariable String functionId) {
        return _functionService.getById(functionId);
    }

    @GetMapping("GetAllFunctions")
    public List<FunctionModel> getAll() {
        return _functionService.getAll();
    }

    @GetMapping("GetFunctionsPaged")
    public PagedResponse<FunctionModel> getFunctionsPaged(PagedRequest<?> request) {
        return _functionService.getAll(request);
    }
}
