package com.example.java_demo.config;

import com.example.java_demo.service.bus.CacheFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.access.AccessDeniedException;
import java.lang.reflect.Method;
import java.util.List;

@Aspect
public class PermissionAspect {

    private final CacheFactory _cacheFactory;

    public PermissionAspect(CacheFactory cacheFactory) {
        _cacheFactory = cacheFactory;
    }

    @Before("@annotation(com.example.java_demo.config.HasPermission) || @within(com.example.java_demo.config.HasPermission) || @annotation(com.example.java_demo.config.HasPermissions) || @within(com.example.java_demo.config.HasPermissions)")
    public void checkPermission(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        HasPermission[] hasPermissions = method.getAnnotationsByType(HasPermission.class);

        if (hasPermissions.length == 0) {
            hasPermissions = joinPoint.getTarget().getClass().getAnnotationsByType(HasPermission.class);
        }

        if (hasPermissions.length > 0) {
            var authentication = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                throw new AccessDeniedException("Access denied: User is not authenticated.");
            }

            boolean isAuthorized = false;

            for (HasPermission requiredPerm : hasPermissions) {
                String requiredFeature = requiredPerm.feature();
                String requiredFunction = requiredPerm.function();

                for (var authority : authentication.getAuthorities()) {
                    String rawRole = authority.getAuthority();
                    if (rawRole == null || rawRole.isBlank()) {
                        continue;
                    }

                    String role = rawRole.replace("ROLE_", "");
                    String redisKey = "role:permissions:" + role;
                    List<?> permissions = _cacheFactory.getCache().get(redisKey, List.class);

                    if (permissions != null) {
                        for (Object obj : permissions) {
                            if (obj instanceof java.util.Map<?, ?> map) {
                                Object feat = map.get("featureId");
                                Object func = map.get("functionId");
                                if (requiredFeature.equalsIgnoreCase(String.valueOf(feat)) && 
                                    requiredFunction.equalsIgnoreCase(String.valueOf(func))) {
                                    isAuthorized = true;
                                    break;
                                }
                            }
                        }
                    }

                    if (isAuthorized) {
                        break;
                    }
                }

                if (isAuthorized) {
                    break;
                }
            }

            if (!isAuthorized) {
                StringBuilder sb = new StringBuilder("Access denied: User does not have any of the required permissions: ");
                for (int i = 0; i < hasPermissions.length; i++) {
                    sb.append("(").append(hasPermissions[i].feature()).append(", ").append(hasPermissions[i].function()).append(")");
                    if (i < hasPermissions.length - 1) {
                        sb.append(", ");
                    }
                }
                throw new AccessDeniedException(sb.toString());
            }
        }
    }
}
