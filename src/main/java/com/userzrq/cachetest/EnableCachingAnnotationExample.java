package com.userzrq.cachetest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EnableCachingAnnotationExample {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(CacheConfig.class);
        ctx.refresh();

        Book book = ctx.getBean(Book.class);

        System.out.println(book.getBook(1));

        System.out.println(book.getBook(1));

        System.out.println(book.getBook(2));
    }
}
