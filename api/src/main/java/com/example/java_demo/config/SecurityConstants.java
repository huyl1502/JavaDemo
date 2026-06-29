package com.example.java_demo.config;

public final class SecurityConstants {

    private SecurityConstants() {
        // Restrict instantiation
    }

    public static final class Feature {
        public static final String USER = "USER";
        public static final String MENU = "MENU";
        public static final String REPORT = "REPORT";
    }

    public static final class Function {
        public static final String VIEW = "VIEW";
        public static final String CREATE = "CREATE";
        public static final String UPDATE = "UPDATE";
        public static final String DELETE = "DELETE";
    }
}
