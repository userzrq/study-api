package com.userzrq.redis;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheStruct<K, V> extends LinkedHashMap<K, V> {

    private final int CACHE_SIZE;

    public LRUCacheStruct(int cacheSize) {

        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        CACHE_SIZE = cacheSize;
    }


    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > CACHE_SIZE;
    }
}
