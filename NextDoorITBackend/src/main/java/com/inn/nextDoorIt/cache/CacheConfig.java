package com.inn.nextDoorIt.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class CacheConfig {

    @Autowired
    private CacheManager cacheManager;


    /*
     * Schedular for clearing all cache in system
     */
    @Scheduled(fixedDelay = 5000) // will clear all cache after 2 minutes
    public void clearCacheEntries() {
        log.info("CLEARING ALL CACHES IN SYSTEM ");

        List<String> cacheList = Arrays.asList("trainingCache", "serviceCache", "serviceDetailCache", "cartCache", "categoryCache", "productCache", "productDetailCache", "reviewRatingCache");
        cacheList.forEach(currentCache -> {
            Cache myCache = cacheManager.getCache(currentCache);
            if (!Objects.isNull(myCache)) { // if something in cache then clear it
                myCache.clear();
            }
        });
    }
}
