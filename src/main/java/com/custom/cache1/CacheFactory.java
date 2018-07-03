package com.custom.cache1;

public class CacheFactory {
 
    public static ICache newInstance(int cacheSize, EvictionStrategy strategy) {
        if (EvictionStrategy.LRU == strategy) 
        {
            return new LRUCache(cacheSize);
        } 
        else if (EvictionStrategy.MRU == strategy) {
            return new MRUCache(cacheSize);
        } 
        else {
            throw new RuntimeException("Invalid EvictionStrategy");
        }
 
    }
 
}