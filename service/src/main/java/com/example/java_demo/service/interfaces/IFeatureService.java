package com.example.java_demo.service.interfaces;

import com.example.java_demo.model.SystemConfig.FeatureModel;
import com.example.java_demo.model.common.PagedRequest;
import com.example.java_demo.model.common.PagedResponse;
import java.util.List;

public interface IFeatureService {
    boolean insert(FeatureModel feature);
    boolean update(FeatureModel feature);
    boolean delete(String featureId);
    FeatureModel getById(String featureId);
    List<FeatureModel> getAll();
    PagedResponse<FeatureModel> getAll(PagedRequest<?> request);
}
