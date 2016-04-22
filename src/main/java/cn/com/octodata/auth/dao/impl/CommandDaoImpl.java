package cn.com.octodata.auth.dao.impl;

import cn.com.octodata.auth.dao.CommandDao;
import cn.com.octodata.auth.util.Config;
import cn.com.octodata.auth.util.JedisPool;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

/**
 * Created by aran on 16-2-24.
 */
@Repository
public class CommandDaoImpl implements CommandDao {
    @Override
    public long rPush(String deviceID, String commandString) {
        try (Jedis jedis = JedisPool.getResource()) {
            jedis.select(Config.getTableCommandIndex());
            jedis.expire(deviceID, Config.getTableCommandSurvivalTime());
            return jedis.rpush(deviceID, commandString);
        }
    }

    @Override
    public long del(String deviceID) {
        try (Jedis jedis = JedisPool.getResource()) {
            jedis.select(Config.getTableCommandIndex());
            return jedis.del(deviceID);
        }
    }

    @Override
    public String lPop(String deviceID) {
        try (Jedis jedis = JedisPool.getResource()) {
            jedis.select(Config.getTableCommandIndex());
            return jedis.lpop(deviceID);
        }
    }
}
