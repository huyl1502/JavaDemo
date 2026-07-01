package com.example.java_demo.controller;

import com.example.java_demo.model.SystemConfig.RightModel;
import com.example.java_demo.service.interfaces.IRightService;
import com.example.java_demo.service.interfaces.ISetupFormService;
import com.example.java_demo.model.common.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/right")
public class RightController {
    private final IRightService _rightService;
    private final ISetupFormService _setupFormService;

    public RightController(IRightService rightService, ISetupFormService setupFormService) {
        _rightService = rightService;
        _setupFormService = setupFormService;
    }

    @PostMapping("SetupForm")
    public CompletableFuture<SetupFormInfo> setupForm() {
        SetupFormFilter filter = new SetupFormFilter();
        filter.catalogs = List.of(CatalogSetupForm.ROLE, CatalogSetupForm.FEATURE, CatalogSetupForm.FUNCTION);
        return _setupFormService.setupForm(filter);
    }

    @PostMapping("InsertRight")
    public boolean insert(@RequestBody RightModel right) {
        return _rightService.insert(right);
    }

    @PutMapping("UpdateRight")
    public boolean update(@RequestBody RightModel right) {
        return _rightService.update(right);
    }

    @DeleteMapping("DeleteRight/{rightId}")
    public boolean delete(@PathVariable String rightId) {
        return _rightService.delete(rightId);
    }

    @GetMapping("GetRightById/{rightId}")
    public RightModel getById(@PathVariable String rightId) {
        return _rightService.getById(rightId);
    }

    @GetMapping("GetAllRights")
    public List<RightModel> getAll() {
        return _rightService.getAll();
    }

    @GetMapping("GetRightsPaged")
    public PagedResponse<RightModel> getRightsPaged(PagedRequest<?> request) {
        return _rightService.getAll(request);
    }
}
