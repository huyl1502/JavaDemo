package com.example.java_demo.service.bus;

import com.example.java_demo.model.common.CatalogSetupForm;
import com.example.java_demo.model.common.SetupFormFilter;
import com.example.java_demo.model.common.SetupFormInfo;
import com.example.java_demo.service.interfaces.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SetupFormService implements ISetupFormService {
    private final IRoleService _roleService;
    private final IFeatureService _featureService;
    private final IFunctionService _functionService;
    private final IMenuService _menuService;
    private final IRightService _rightService;
    private final IUserService _userService;

    public SetupFormService(
            IRoleService roleService,
            IFeatureService featureService,
            IFunctionService functionService,
            IMenuService menuService,
            IRightService rightService,
            IUserService userService) {
        _roleService = roleService;
        _featureService = featureService;
        _functionService = functionService;
        _menuService = menuService;
        _rightService = rightService;
        _userService = userService;
    }

    @Override
    public CompletableFuture<SetupFormInfo> setupForm(SetupFormFilter filter) {
        SetupFormInfo res = new SetupFormInfo();
        List<CatalogSetupForm> catalogs = filter.catalogs;
        if (catalogs == null || catalogs.isEmpty()) {
            return CompletableFuture.completedFuture(res);
        }

        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (CatalogSetupForm ct : catalogs) {
            switch (ct) {
                case ROLE:
                    futures.add(CompletableFuture.runAsync(() -> res.listRoles = _roleService.getAll()));
                    break;
                case FEATURE:
                    futures.add(CompletableFuture.runAsync(() -> res.listFeatures = _featureService.getAll()));
                    break;
                case FUNCTION:
                    futures.add(CompletableFuture.runAsync(() -> res.listFunctions = _functionService.getAll()));
                    break;
                case MENU:
                    futures.add(CompletableFuture.runAsync(() -> res.listMenus = _menuService.getAll()));
                    break;
                case RIGHT:
                    futures.add(CompletableFuture.runAsync(() -> res.listRights = _rightService.getAll()));
                    break;
                case USER:
                    futures.add(CompletableFuture.runAsync(() -> res.listUsers = _userService.getAll()));
                    break;
            }
        }

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .thenApply(v -> res);
    }
}
