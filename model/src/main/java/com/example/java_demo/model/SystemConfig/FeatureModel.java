package com.example.java_demo.model.SystemConfig;

import lombok.*;
import org.bson.codecs.pojo.annotations.*;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeatureModel {
    @BsonId
    private ObjectId id;
    private String FeatureId;
    private String FeatureName;
    private boolean Enabled;
}
