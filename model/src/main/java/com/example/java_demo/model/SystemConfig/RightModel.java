package com.example.java_demo.model.SystemConfig;

import lombok.*;
import org.bson.codecs.pojo.annotations.*;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RightModel {
    @BsonId
    private ObjectId id;
    private String RightId;
    private String RoleId;
    private String FeatureId;
    private String FunctionId;
}
