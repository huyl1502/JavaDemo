package com.example.java_demo.service.bus;

import com.example.java_demo.model.UserModel;
import com.example.java_demo.repository.interfaces.IUserDAL;
import com.example.java_demo.service.interfaces.*;

public class UserService implements IUserService {
    private final IUserDAL _userDAL;
    public UserService(IUserDAL userDAL){
        _userDAL = userDAL;
    }

    @Override
    public boolean insert(UserModel user) {
        _userDAL.insert(user);
        return true;
    }
}
