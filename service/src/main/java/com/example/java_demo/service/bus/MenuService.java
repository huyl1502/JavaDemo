package com.example.java_demo.service.bus;

import com.example.java_demo.model.SystemConfig.*;
import com.example.java_demo.repository.interfaces.*;
import com.example.java_demo.service.interfaces.*;
import java.util.*;

public class MenuService implements IMenuService {
    private final IMenuDAL _menuDAL;

    public MenuService(IMenuDAL menuDAL) {
        _menuDAL = menuDAL;
    }

    @Override
    public boolean insert(MenuModel menu) {
        return _menuDAL.insert(menu);
    }

    @Override
    public boolean update(MenuModel menu) {
        return _menuDAL.update(menu);
    }

    @Override
    public boolean delete(String menuId) {
        return _menuDAL.delete(menuId);
    }

    @Override
    public MenuModel getById(String menuId) {
        return _menuDAL.getById(menuId);
    }

    @Override
    public List<MenuModel> getAll() {
        return _menuDAL.getAll();
    }

    @Override
    public List<MenuModel> getMenuUser(String userId) {
        return _menuDAL.getAll();
    }
}
