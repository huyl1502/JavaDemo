package com.example.java_demo.model.SystemConfig;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuModel {
    private Integer id;
    private String MenuId;
    private String MenuName;
    private String ParentMenuId;
    private String Url;
    private String Icon;
    private String FunctionId;
    private boolean Enabled;
}
