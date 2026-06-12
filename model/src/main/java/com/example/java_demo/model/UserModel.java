package com.example.java_demo.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private String id;
    private String name;
    private Integer age;
}