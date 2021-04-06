package com.userzrq.redis;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 基于LinkedHashMap设计的LRU数据结构
 *
 * @param <K>
 * @param <V>
 * @author 10017
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private final int CACHE_SIZE;


    /**
     * 根据传递的参数构造能缓存多少数据，构造一个LinkedHashMap
     *
     * @param cacheSize
     */
    public LRUCache(int cacheSize) {
        /**
         * 调用 LinkedHashMap 构造器进行构造
         * accessOrder = true 表示根据访问顺序进行排序，最近访问的放在头部，最老访问的放在尾部
         * The iteration ordering method for this linked hash map:
         * <tt>true</tt>  for access-order,
         * <tt>false</tt> for insertion-order.
         *
         * @serial
         */
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        CACHE_SIZE = cacheSize;
    }

    /**
     * 将尾部最老访问的数据移除掉
     *
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > CACHE_SIZE;
    }
}
