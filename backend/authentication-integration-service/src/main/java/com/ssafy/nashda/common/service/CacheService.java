package com.ssafy.nashda.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CacheService {
    private final CacheManager cacheManager;

    public String saveCache(String value) {
        Cache cache = cacheManager.getCache("MyCache");
        String id = UUID.randomUUID().toString();
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        if (cache != null) {
            cache.put(id, bytes);
        }
        return id;
    }

    public String getCache(String id) {
        Cache cache = cacheManager.getCache("MyCache");
        String value = null;
        byte[] bytes = cache.get(id, byte[].class);
        if (bytes != null) {
            value = new String(bytes, StandardCharsets.UTF_8);
        }

        return value;
    }
}
