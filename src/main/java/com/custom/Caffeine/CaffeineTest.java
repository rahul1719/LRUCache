package com.custom.Caffeine;

import com.custom.cache.Vehicle;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class CaffeineTest {

    Cache<String, Vehicle> caffeineCache = Caffeine.newBuilder()
            .expireAfterWrite(1,TimeUnit.MINUTES)
            .maximumSize(100)
            .build();

    //caffeineCache.put(key, Vehicle);
    //dataObject = cache.getIfPresent(key);

}
