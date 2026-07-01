package com.example.java_demo.service.interfaces;

import com.example.java_demo.model.common.SetupFormFilter;
import com.example.java_demo.model.common.SetupFormInfo;
import java.util.concurrent.CompletableFuture;

public interface ISetupFormService {
    CompletableFuture<SetupFormInfo> setupForm(SetupFormFilter filter);
}
