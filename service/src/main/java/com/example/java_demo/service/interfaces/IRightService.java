package com.example.java_demo.service.interfaces;

import com.example.java_demo.model.SystemConfig.RightModel;
import com.example.java_demo.model.common.PagedRequest;
import com.example.java_demo.model.common.PagedResponse;
import java.util.List;

public interface IRightService {
    boolean insert(RightModel right);
    boolean update(RightModel right);
    boolean delete(String rightId);
    RightModel getById(String rightId);
    List<RightModel> getAll();
    PagedResponse<RightModel> getAll(PagedRequest<?> request);
}
