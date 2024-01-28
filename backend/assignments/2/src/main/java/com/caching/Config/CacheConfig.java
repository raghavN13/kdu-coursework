package com.caching.Config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public Caffeine caffeineConfig() {
        return Caffeine.newBuilder()
                .maximumSize(100)  // Set your maximum cache size
                .expireAfterWrite(30, TimeUnit.MINUTES)  // Set your expiration time
                .recordStats();  // Enable statistics for LFU

    }

    /**
     * Creates the cachemanager which is used for caching in the application
     * @param caffeine
     * @return instance of the cache manager
     */
    @Bean
    public CacheManager cacheManager(Caffeine caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
}
