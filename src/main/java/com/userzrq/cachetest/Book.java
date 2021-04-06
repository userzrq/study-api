package com.userzrq.cachetest;

import org.springframework.cache.annotation.Cacheable;

public class Book {

    /**
     * value : 缓存的名字  ,key ： 缓存map中的key
     *
     * @param id
     * @return
     */
    @Cacheable(value = {"sampleCache"}, keyGenerator = "")
    public String getBook(int id) {
        System.out.println("Method executed");
        if (id == 1) {
            return "Book 1";
        } else {
            return "Book 2";
        }
    }
}
