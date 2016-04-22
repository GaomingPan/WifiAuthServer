package cn.com.octodata.auth.dao.impl;

import cn.com.octodata.auth.dao.UserAuthInfoDao;
import cn.com.octodata.auth.model.UserAuthInfo;
import cn.com.octodata.auth.util.Config;
import cn.com.octodata.auth.util.JedisPool;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

/**
 * Created by aran on 16-2-24.
 */
@Repository
public class UserAuthInfoDaoImpl implements UserAuthInfoDao {
    @Override
    public long hSet(String areaId, String userMac, String userAuthInfoJsonString) {
        try (Jedis jedis = JedisPool.getResource()) {
            jedis.select(Config.getTableUserInfoIndex());
            long result = jedis.hset(areaId, userMac, userAuthInfoJsonString);
            jedis.expire(userMac, Config.getTableUserInfoSurvivalTime());
            return result;
        }
    }

    @Override
    public long hDel(String areaId, String userMac) {
        try (Jedis jedis = JedisPool.getResource()) {
            jedis.select(Config.getTableUserInfoIndex());
            return jedis.hdel(areaId, userMac);
        }
    }

    @Override
    public String hGet(String areaId, String userMac) {
        try (Jedis jedis = JedisPool.getResource()) {
            jedis.select(Config.getTableUserInfoIndex());
            return jedis.hget(areaId, userMac);
        }
    }

    @Override
    public long hSet(String areaId, String userMac, UserAuthInfo userAuthInfo) {
        return this.hSet(areaId, userMac, JSON.toJSONString(userAuthInfo));
    }

    @Override
    public UserAuthInfo hGetUserAuthInfo(String areaId, String userMac) {
        return JSON.parseObject(this.hGet(areaId, userMac), UserAuthInfo.class);
    }
}
