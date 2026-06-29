package com.example.java_demo.model.SystemConfig;

import lombok.*;
import org.bson.codecs.pojo.annotations.*;
import org.bson.types.ObjectId;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FunctionModel {
    @BsonId
    private ObjectId id;
    private String FunctionId;
    private String FunctionName;
    private String ParentFunctionId;
    private boolean Enabled;
    @BsonIgnore
    private List<FunctionModel> Children;
}
