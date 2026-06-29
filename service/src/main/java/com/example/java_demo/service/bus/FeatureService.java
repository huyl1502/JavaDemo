package com.example.java_demo.service.bus;

import com.example.java_demo.model.SystemConfig.FeatureModel;
import com.example.java_demo.model.common.PagedRequest;
import com.example.java_demo.model.common.PagedResponse;
import com.example.java_demo.repository.interfaces.IFeatureDAL;
import com.example.java_demo.service.interfaces.IFeatureService;
import java.util.List;

public class FeatureService implements IFeatureService {
    private final IFeatureDAL _featureDAL;

    public FeatureService(IFeatureDAL featureDAL) {
        _featureDAL = featureDAL;
    }

    @Override
    public boolean insert(FeatureModel feature) {
        return _featureDAL.insert(feature);
    }

    @Override
    public boolean update(FeatureModel feature) {
        return _featureDAL.update(feature);
    }

    @Override
    public boolean delete(String featureId) {
        return _featureDAL.delete(featureId);
    }

    @Override
    public FeatureModel getById(String featureId) {
        return _featureDAL.getById(featureId);
    }

    @Override
    public List<FeatureModel> getAll() {
        return _featureDAL.getAll();
    }

    @Override
    public PagedResponse<FeatureModel> getAll(PagedRequest<?> request) {
        return _featureDAL.getAll(request);
    }
}
