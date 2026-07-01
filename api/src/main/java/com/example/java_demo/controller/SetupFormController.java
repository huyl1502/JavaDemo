package com.example.java_demo.controller;

import com.example.java_demo.model.common.SetupFormFilter;
import com.example.java_demo.model.common.SetupFormInfo;
import com.example.java_demo.service.interfaces.ISetupFormService;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/setup-form")
public class SetupFormController {
    private final ISetupFormService _setupFormService;

    public SetupFormController(ISetupFormService setupFormService) {
        _setupFormService = setupFormService;
    }

    @PostMapping
    public CompletableFuture<SetupFormInfo> setupForm(@RequestBody SetupFormFilter filter) {
        return _setupFormService.setupForm(filter);
    }
}
