package cn.com.octodata.auth.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by aran on 16-2-24.
 */
public class JDBCPool {

    private static final BasicDataSource basicDataSource;

    static {
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://" + Config.getMysqlHost() + ":" + Config.getMysqlPort() + "/" + Config.getMysqlDbName() + "?useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        basicDataSource.setUsername(Config.getMysqlUsername());
        basicDataSource.setPassword(Config.getMysqlPassword());
//        basicDataSource.setInitialSize(Config.getMysqlInitialSize());
//        basicDataSource.setMaxTotal(Config.getMysqlMaxTotal());
//        basicDataSource.setMaxIdle(Config.getMysqlMaxIdle());
//        basicDataSource.setMinIdle(Config.getMysqlMinIdle());
//        basicDataSource.setMaxWaitMillis(Config.getMysqlMaxWaitMillis());
    }

    public static Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }
}
