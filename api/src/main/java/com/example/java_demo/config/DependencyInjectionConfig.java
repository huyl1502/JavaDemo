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

    @Bean
    public IMenuDAL menuDAL(MongoDatabase database) {
        return new MenuDAL(database);
    }
    @Bean
    public IMenuService menuService(IMenuDAL menuDAL) {
        return new MenuService(menuDAL);
    }

    @Bean
    public IFunctionDAL functionDAL(MongoDatabase database) {
        return new FunctionDAL(database);
    }
    @Bean
    public IFunctionService functionService(IFunctionDAL functionDAL) {
        return new FunctionService(functionDAL);
    }

    @Bean
    public IFeatureDAL featureDAL(MongoDatabase database) {
        return new FeatureDAL(database);
    }
    @Bean
    public IFeatureService featureService(IFeatureDAL featureDAL) {
        return new FeatureService(featureDAL);
    }

    @Bean
    public IRoleDAL roleDAL(MongoDatabase database) {
        return new RoleDAL(database);
    }
    @Bean
    public IRoleService roleService(IRoleDAL roleDAL) {
        return new RoleService(roleDAL);
    }

    @Bean
    public IRightDAL rightDAL(MongoDatabase database) {
        return new RightDAL(database);
    }
    @Bean
    public IRightService rightService(IRightDAL rightDAL) {
        return new RightService(rightDAL);
    }

    @Bean
    public ICacheService redisCacheService(org.springframework.data.redis.core.RedisTemplate<String, Object> redisTemplate) {
        return new RedisCacheService(redisTemplate);
    }

    @Bean
    public CacheFactory cacheFactory(ICacheService redisCacheService) {
        return new CacheFactory(redisCacheService);
    }

    @Bean
    public PermissionAspect permissionAspect(CacheFactory cacheFactory) {
        return new PermissionAspect(cacheFactory);
    }

    @Bean
    public ISetupFormService setupFormService(
            IRoleService roleService,
            IFeatureService featureService,
            IFunctionService functionService,
            IMenuService menuService,
            IRightService rightService,
            IUserService userService) {
        return new SetupFormService(roleService, featureService, functionService, menuService, rightService, userService);
    }
}
