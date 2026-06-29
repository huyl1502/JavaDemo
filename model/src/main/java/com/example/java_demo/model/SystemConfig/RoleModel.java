package com.example.java_demo.model.SystemConfig;

import lombok.*;
import org.bson.codecs.pojo.annotations.*;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleModel {
    @BsonId
    private ObjectId id;
    private String RoleId;
    private String RoleName;
    private boolean Enabled;
}
