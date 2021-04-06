package com.userzrq.redis.redisLock;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @author zhangruiqi 10017
 * @create 2021/3/16
 */
public class RedisTool {

    /**
     * redis返回的结果
     */
    private static final String LOCK_SUCCESS = "OK";
    /**
     * If not exist 一个客户端只能持有一次锁，如果已经存在则
     */
    private static final String SET_IF_NOT_EXIST = "NX";
    /**
     * 设置过期时间
     */
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    /**
     * 尝试获取分布式锁
     *
     * @param jedis      Redis客户端
     * @param lockKey    锁
     * @param requestId  请求标识，解锁的时候可以知道这把锁是哪个请求加的了，在解锁的时候可以有依据
     * @param expireTime 超期时间  防止死锁，自动放锁
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {

        // 一个原子性的操作
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;

        /**
         * 只会有两种结果
         * 1. 当前没有锁（key不存在），那么就进行加锁操作，并对锁设置个有效期，同时value表示加锁的客户端
         * 2. 已有锁存在，不做任何操作
         */
    }



    private static final Long RELEASE_SUCCESS = 1L;
    /**
     * 释放分布式锁
     * 首先用key获取锁对应的value值，检查是否与requestId相等，如果相等则删除锁（解锁）
     *
     * @param jedis     Redis客户端
     * @param lockKey   锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        // 将参数传入LUA脚本内，使用脚本保证执行命令的原子性
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }
}
