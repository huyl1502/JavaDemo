package com.example.java_demo.service.bus;

import com.example.java_demo.model.SystemConfig.MenuModel;
import com.example.java_demo.repository.interfaces.IMenuDAL;
import com.example.java_demo.service.interfaces.IMenuService;
import java.util.List;

public class MenuService implements IMenuService {
    private final IMenuDAL _menuDAL;
    
    public MenuService(IMenuDAL menuDAL){
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
}
