package cn.com.octodata.auth.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by aran on 16-2-24.
 */
public class JedisPool {
    private static final redis.clients.jedis.JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(Config.getRedisMaxTotal());
//        jedisPoolConfig.setMaxIdle(Config.getRedisMaxIdle());
//        jedisPoolConfig.setMinIdle(Config.getMysqlMinIdle());
//        jedisPoolConfig.setMaxWaitMillis(Config.getRedisMaxWaitMillis());
//        jedisPoolConfig.setTestOnBorrow(false);
//        jedisPoolConfig.setTestOnReturn(false);
        jedisPool = new redis.clients.jedis.JedisPool(jedisPoolConfig, Config.getRedisHost(), Config.getRedisPort(), 1000, Config.getRedisPassword());
//        jedisPool = new redis.clients.jedis.JedisPool(jedisPoolConfig, Config.getRedisHost(), Config.getRedisPort());
    }

    public static Jedis getResource() {
        return jedisPool.getResource();
    }
}
