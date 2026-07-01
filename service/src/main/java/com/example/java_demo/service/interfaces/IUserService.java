package com.example.java_demo.service.interfaces;

import com.example.java_demo.model.*;
import java.util.List;

public interface IUserService {
    boolean insert(UserModel user);
    List<UserModel> getAll();
}
