package com.ssafy.nashda.common.service;

import com.ssafy.nashda.simulGPT.dto.MemorizeMessageReqDto;
import com.ssafy.nashda.simulGPT.dto.MessageReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CacheService {
    private final CacheManager cacheManager;

    public String saveCache(MessageReqDto messageReqDto) {
        Cache cache = cacheManager.getCache("MyCache");
        String id = UUID.randomUUID().toString();

        byte[] bytes = null;

        // 캐시에 객체 저장
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutput out = new ObjectOutputStream(bos)) {
                 out.writeObject(messageReqDto);
                 bytes = bos.toByteArray();
             } catch (IOException e) {
                 e.printStackTrace();
        }
        if (cache != null) {
            cache.put(id, bytes);
        }

        return id;
    }

    public MemorizeMessageReqDto getCache(String id) {
        Cache cache = cacheManager.getCache("MyCache");
        byte[] bytes = cache.get(id, byte[].class);

        MemorizeMessageReqDto result = null;

        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInput in = new ObjectInputStream(bis)) {
            result = (MemorizeMessageReqDto) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();;
        }

        return result;
    }

    public void clearCache(String id) {
        Cache cache = cacheManager.getCache("MyCache");
        if (cache != null) {
            cache.evict(id);
        }
    }
}
