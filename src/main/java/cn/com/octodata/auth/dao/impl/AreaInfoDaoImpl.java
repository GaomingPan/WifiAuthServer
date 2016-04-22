package cn.com.octodata.auth.dao.impl;

import cn.com.octodata.auth.dao.AreaInfoDao;
import cn.com.octodata.auth.model.AreaInfo;
import cn.com.octodata.auth.util.Config;
import cn.com.octodata.auth.util.JDBCPool;
import cn.com.octodata.auth.util.JedisPool;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by aran on 16-2-24.
 */
@Repository
public class AreaInfoDaoImpl implements AreaInfoDao {
    @Override
    public String set(String areaID, String areaInfoJsonString) {
        try (Jedis jedis = JedisPool.getResource()) {
            jedis.select(Config.getTableAreaInfoIndex());
            return jedis.setex(areaID, Config.getTableAreaInfoSurvivalTime(), areaInfoJsonString);
        }
    }

    @Override
    public long del(String areaID) {
        try (Jedis jedis = JedisPool.getResource()) {
            jedis.select(Config.getTableAreaInfoIndex());
            return jedis.del(areaID);
        }
    }

    @Override
    public String get(String areaID) throws Exception {
        try (Jedis jedis = JedisPool.getResource()) {
            jedis.select(Config.getTableAreaInfoIndex());
            String areaInfoJsonString = jedis.get(areaID);
            if (areaInfoJsonString == null) {
                try (Connection connection = JDBCPool.getConnection()) {
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT areaInfoJsonString FROM area WHERE areaID = ?");
                    preparedStatement.setString(1, areaID);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        areaInfoJsonString = resultSet.getString(1);
                    } else {
                        areaInfoJsonString = "0";
                    }
                }
                jedis.setex(areaID, Config.getTableAreaInfoSurvivalTime(), areaInfoJsonString);
            }
            return areaInfoJsonString;
        }
    }

    @Override
    public AreaInfo getAreaInfo(String areaID) throws Exception {
        return JSON.parseObject(this.get(areaID), AreaInfo.class);
    }

}
