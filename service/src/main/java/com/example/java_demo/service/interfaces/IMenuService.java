package com.example.java_demo.service.interfaces;

import com.example.java_demo.model.SystemConfig.MenuModel;
import java.util.List;

public interface IMenuService {
    boolean insert(MenuModel menu);

    boolean update(MenuModel menu);

    boolean delete(String menuId);

    MenuModel getById(String menuId);

    List<MenuModel> getAll();

    List<MenuModel> getMenuUser(String userId);
}
