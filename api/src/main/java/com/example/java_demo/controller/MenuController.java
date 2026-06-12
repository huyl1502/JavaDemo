package com.example.java_demo.controller;

import com.example.java_demo.model.SystemConfig.MenuModel;
import com.example.java_demo.service.interfaces.IMenuService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    private final IMenuService _menuService;

    public MenuController(IMenuService menuService) {
        _menuService = menuService;
    }

    @PostMapping("InsertMenu")
    public boolean insert(@RequestBody MenuModel menu) {
        return _menuService.insert(menu);
    }

    @PutMapping("UpdateMenu")
    public boolean update(@RequestBody MenuModel menu) {
        return _menuService.update(menu);
    }

    @DeleteMapping("DeleteMenu/{menuId}")
    public boolean delete(@PathVariable String menuId) {
        return _menuService.delete(menuId);
    }

    @GetMapping("GetMenuById/{menuId}")
    public MenuModel getById(@PathVariable String menuId) {
        return _menuService.getById(menuId);
    }

    @GetMapping("GetAllMenus")
    public List<MenuModel> getAll() {
        return _menuService.getAll();
    }
}
