package com.example.java_demo.config;

import com.example.java_demo.repository.dal.*;
import com.example.java_demo.repository.interfaces.*;
import com.example.java_demo.service.bus.*;
import com.example.java_demo.service.interfaces.*;
import com.mongodb.client.*;
import org.springframework.context.annotation.*;

@Configuration
public class DependencyInjectionConfig {
    @Bean
    public ITestService testService() {
        return new TestService();
    }

    @Bean
    public IUserDAL userDAL(MongoDatabase database) {
        return new UserDAL(database);
    }
    @Bean
    public IUserService userService(IUserDAL userDAL) {
        return new UserService(userDAL);
    }
}
