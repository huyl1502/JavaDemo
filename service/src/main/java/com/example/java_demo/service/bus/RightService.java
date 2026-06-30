package com.example.java_demo.service.bus;

import com.example.java_demo.model.SystemConfig.RightModel;
import com.example.java_demo.model.common.PagedRequest;
import com.example.java_demo.model.common.PagedResponse;
import com.example.java_demo.repository.interfaces.IRightDAL;
import com.example.java_demo.service.interfaces.IRightService;
import java.util.List;
import java.util.UUID;

public class RightService implements IRightService {
    private final IRightDAL _rightDAL;

    public RightService(IRightDAL rightDAL) {
        _rightDAL = rightDAL;
    }

    @Override
    public boolean insert(RightModel right) {
        if (right.getRightId() == null || right.getRightId().isBlank()) {
            right.setRightId(UUID.randomUUID().toString());
        }
        return _rightDAL.insert(right);
    }

    @Override
    public boolean update(RightModel right) {
        return _rightDAL.update(right);
    }

    @Override
    public boolean delete(String rightId) {
        return _rightDAL.delete(rightId);
    }

    @Override
    public RightModel getById(String rightId) {
        return _rightDAL.getById(rightId);
    }

    @Override
    public List<RightModel> getAll() {
        return _rightDAL.getAll();
    }

    @Override
    public PagedResponse<RightModel> getAll(PagedRequest<?> request) {
        return _rightDAL.getAll(request);
    }
}
