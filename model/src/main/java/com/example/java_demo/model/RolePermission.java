package com.example.java_demo.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission {
    private String roleId;
    private String featureId;
    private String functionId;
}
