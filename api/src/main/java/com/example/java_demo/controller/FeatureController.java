package com.example.java_demo.controller;

import com.example.java_demo.model.SystemConfig.FeatureModel;
import com.example.java_demo.service.interfaces.IFeatureService;
import com.example.java_demo.model.common.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/feature")
public class FeatureController {
    private final IFeatureService _featureService;

    public FeatureController(IFeatureService featureService) {
        _featureService = featureService;
    }

    @PostMapping("InsertFeature")
    public boolean insert(@RequestBody FeatureModel feature) {
        return _featureService.insert(feature);
    }

    @PutMapping("UpdateFeature")
    public boolean update(@RequestBody FeatureModel feature) {
        return _featureService.update(feature);
    }

    @DeleteMapping("DeleteFeature/{featureId}")
    public boolean delete(@PathVariable String featureId) {
        return _featureService.delete(featureId);
    }

    @GetMapping("GetFeatureById/{featureId}")
    public FeatureModel getById(@PathVariable String featureId) {
        return _featureService.getById(featureId);
    }

    @GetMapping("GetAllFeatures")
    public List<FeatureModel> getAll() {
        return _featureService.getAll();
    }

    @GetMapping("GetFeaturesPaged")
    public PagedResponse<FeatureModel> getFeaturesPaged(PagedRequest<?> request) {
        return _featureService.getAll(request);
    }
}
