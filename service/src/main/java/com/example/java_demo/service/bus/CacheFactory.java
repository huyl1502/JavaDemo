package com.example.java_demo.service.bus;

import com.example.java_demo.service.interfaces.ICacheService;

public class CacheFactory {
    private final ICacheService _redisCacheService;

    public CacheFactory(ICacheService redisCacheService) {
        _redisCacheService = redisCacheService;
    }

    public ICacheService getCache() {
        return _redisCacheService;
    }
}
