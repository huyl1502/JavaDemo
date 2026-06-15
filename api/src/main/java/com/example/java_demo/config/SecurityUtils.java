package com.example.java_demo.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class SecurityUtils {

    /**
     * Lấy UserId (claim "sub") của user hiện tại từ JWT token.
     *
     * @return UserId dạng String, hoặc null nếu chưa xác thực.
     */
    public static String getCurrentUserId() {
        Jwt jwt = getCurrentJwt();
        if (jwt != null) {
            return jwt.getSubject(); // claim "sub"
            // Nếu token dùng claim tên khác, thay bằng:
            // return jwt.getClaim("userId");
            // return jwt.getClaim("oid");         // Azure AD
            // return jwt.getClaim("preferred_username"); // Keycloak
        }
        return null;
    }

    /**
     * Lấy toàn bộ JWT object của user hiện tại.
     *
     * @return Jwt object, hoặc null nếu chưa xác thực.
     */
    public static Jwt getCurrentJwt() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt;
        }
        return null;
    }

    /**
     * Lấy giá trị của một claim bất kỳ trong JWT token.
     *
     * @param claimName Tên claim cần lấy (vd: "email", "roles", "userId")
     * @return Giá trị claim dạng String, hoặc null nếu không tìm thấy.
     */
    public static String getClaim(String claimName) {
        Jwt jwt = getCurrentJwt();
        if (jwt != null) {
            Object claim = jwt.getClaim(claimName);
            return claim != null ? claim.toString() : null;
        }
        return null;
    }

    /**
     * Log tất cả các claims trong JWT token ra console (dùng để debug).
     */
    public static void printAllClaims() {
        Jwt jwt = getCurrentJwt();
        if (jwt != null) {
            System.out.println("[SecurityUtils] JWT Claims: " + jwt.getClaims());
        } else {
            System.out.println("[SecurityUtils] Không có JWT token.");
        }
    }
}
