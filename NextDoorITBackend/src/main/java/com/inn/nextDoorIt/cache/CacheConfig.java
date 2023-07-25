package com.inn.nextDoorIt.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CacheConfig {

    @Autowired
    private CacheManager cacheManager;


    /*
     * Schedular for clearing all cache in system
     */
    @Scheduled(fixedDelay = 30000) // Schedule to run every 1 hour (adjust as needed)
    public void clearCacheEntries() {
        log.info("CLEARING ALL CACHES IN SYSTEM ");
        Cache cache = cacheManager.getCache("serviceCache"); // Replace "cacheName" with your cache name
        Cache serviceDetailCache = cacheManager.getCache("serviceDetailCache");
        Cache cartCache = cacheManager.getCache("cartCache");
        Cache categoryCache = cacheManager.getCache("categoryCache");
        Cache productCache = cacheManager.getCache("productCache");
        Cache productDetailCache = cacheManager.getCache("productDetailCache");
        if (cache != null) {
            cache.clear();
        }
        if (serviceDetailCache != null) {
            serviceDetailCache.clear();
        }
        if (cartCache != null) {
            cartCache.clear();
        }
        if (categoryCache != null) {
            categoryCache.clear();
        }
        if (productCache != null) {
            productCache.clear();
        }
        if (productDetailCache != null) {
            productDetailCache.clear();
        }
    }
}
