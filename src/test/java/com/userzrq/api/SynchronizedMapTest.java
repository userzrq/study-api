package com.userzrq.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SynchronizedMapTest {

    Map<String, Object> innerMap = new HashMap<>();
    Map<String, Object> map = Collections.synchronizedMap(new HashMap<>());

    @Test
    public void testSynchronized() {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            CompletableFuture.runAsync(() -> {
                // innerMap.put(Integer.valueOf(finalI).toString(), Integer.valueOf(finalI));
                map.put(Integer.valueOf(finalI).toString(), Integer.valueOf(finalI));
            });
        }

        Iterator<Map.Entry<String, Object>> innerIterator = innerMap.entrySet().iterator();
        while (innerIterator.hasNext()) {
            System.out.println("innerMap:" + innerIterator.next());
        }

        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println("map:" + iterator.next());
        }
    }
}
