package com.example.java_demo.service.interfaces;

public interface ICacheService {
    <T> T get(String key, Class<T> clazz);
    void set(String key, Object value);
    void set(String key, Object value, long ttlSeconds);
    boolean delete(String key);
    boolean hasKey(String key);
}
