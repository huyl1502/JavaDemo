package com.example.java_demo.service.bus;

import com.example.java_demo.model.SystemConfig.*;
import com.example.java_demo.model.common.PagedRequest;
import com.example.java_demo.model.common.PagedResponse;
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
    public PagedResponse<MenuModel> getAll(PagedRequest<?> request) {
        return _menuDAL.getAll(request);
    }

    @Override
    public List<MenuModel> getMenuUser(String userId) {
        List<MenuModel> allMenus = _menuDAL.getAll();

        // Group menu theo ParentMenuId
        Map<String, List<MenuModel>> groupByParent = new HashMap<>();
        for (MenuModel menu : allMenus) {
            String parentId = menu.getParentMenuId() != null ? menu.getParentMenuId() : "";
            groupByParent.computeIfAbsent(parentId, k -> new ArrayList<>()).add(menu);
        }

        // Gán children cho từng node, null nếu không có con
        for (MenuModel menu : allMenus) {
            List<MenuModel> children = groupByParent.get(menu.getMenuId());
            menu.setChildren(children != null && !children.isEmpty() ? children : null);
        }

        // Trả về các node gốc (ParentMenuId null hoặc rỗng)
        List<MenuModel> rootMenus = new ArrayList<>();
        for (MenuModel menu : allMenus) {
            String parentId = menu.getParentMenuId();
            if (parentId == null || parentId.isEmpty()) {
                rootMenus.add(menu);
            }
        }
        return rootMenus;
    }
}
