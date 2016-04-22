package cn.com.octodata.auth.dao.impl;

import cn.com.octodata.auth.dao.BindingDao;
import cn.com.octodata.auth.util.Config;
import cn.com.octodata.auth.util.JDBCPool;
import cn.com.octodata.auth.util.JedisPool;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by aran on 16-2-24.
 */
@Repository
public class BindingDaoImpl implements BindingDao {
    @Override
    public String set(String deviceID, String areaID) {
        try (Jedis jedis = JedisPool.getResource()) {
            jedis.select(Config.getTableBindingInfoIndex());
            return jedis.setex(deviceID, Config.getTableBindingInfoSurvivalTime(), areaID);
        }
    }

    @Override
    public long del(String deviceID) {
        try (Jedis jedis = JedisPool.getResource()) {
            jedis.select(Config.getTableBindingInfoIndex());
            return jedis.del(deviceID);
        }
    }


    @Override
    public String get(String deviceID) throws Exception {
        try (Jedis jedis = JedisPool.getResource()) {
            jedis.select(Config.getTableBindingInfoIndex());
            String areaID = jedis.get(deviceID);
            if (areaID == null) {
                try (Connection connection = JDBCPool.getConnection()) {
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT areaID FROM device WHERE deviceID = ?");
                    preparedStatement.setString(1, deviceID);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        areaID = resultSet.getString(1);
                    } else {
                        areaID = "0";
                    }
                }
                jedis.setex(deviceID, Config.getTableBindingInfoSurvivalTime(), areaID);
            }
            return areaID;
        }
    }
}
