package com.example.java_demo.model.common;

import java.util.List;
import lombok.Data;

@Data
public class SetupFormFilter {
    public List<CatalogSetupForm> catalogs;
    public String userId;
}
