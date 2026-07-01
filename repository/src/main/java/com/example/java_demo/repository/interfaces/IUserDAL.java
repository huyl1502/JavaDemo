package com.example.java_demo.repository.interfaces;

import com.example.java_demo.model.*;
import java.util.List;

public interface IUserDAL {
    boolean insert(UserModel user);
    List<UserModel> getAll();
}
