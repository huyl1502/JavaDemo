package com.example.java_demo.model.common;

import com.example.java_demo.model.SystemConfig.*;
import com.example.java_demo.model.UserModel;
import java.util.List;
import lombok.Data;

@Data
public class SetupFormInfo {
    public List<RoleModel> listRoles;
    public List<FeatureModel> listFeatures;
    public List<FunctionModel> listFunctions;
    public List<MenuModel> listMenus;
    public List<RightModel> listRights;
    public List<UserModel> listUsers;
}
