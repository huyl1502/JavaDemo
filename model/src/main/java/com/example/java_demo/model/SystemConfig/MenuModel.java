package com.example.java_demo.model.SystemConfig;

import lombok.*;
import org.bson.codecs.pojo.annotations.*;
import org.bson.types.ObjectId;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuModel {
    @BsonId
    private ObjectId id;
    private String MenuId;
    private String MenuName;
    private String ParentMenuId;
    private String Url;
    private String Icon;
    private String FunctionId;
    private boolean Enabled;
    @BsonIgnore
    private List<MenuModel> Children;
}
