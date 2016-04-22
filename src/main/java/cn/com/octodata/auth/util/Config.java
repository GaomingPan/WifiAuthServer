package cn.com.octodata.auth.util;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by aran on 16-2-24.
 */
public class Config {
    private static final ResourceBundle resourceBundle = PropertyResourceBundle.getBundle("config");
    //    数据库配置获取
    private static final String MYSQL_HOST = resourceBundle.getString("MYSQL_HOST");
    private static final int MYSQL_PORT = Integer.parseInt(resourceBundle.getString("MYSQL_PORT"));
    private static final String MYSQL_DB_NAME = resourceBundle.getString("MYSQL_DB_NAME");
    private static final String MYSQL_USERNAME = resourceBundle.getString("MYSQL_USERNAME");
    private static final String MYSQL_PASSWORD = resourceBundle.getString("MYSQL_PASSWORD");
    private static final int MYSQL_INITIAL_SIZE = Integer.parseInt(resourceBundle.getString("MYSQL_INITIAL_SIZE"));
    private static final int MYSQL_MAX_TOTAL = Integer.parseInt(resourceBundle.getString("MYSQL_MAX_TOTAL"));
    private static final int MYSQL_MAX_IDLE = Integer.parseInt(resourceBundle.getString("MYSQL_MAX_IDLE"));
    private static final int MYSQL_MIN_IDLE = Integer.parseInt(resourceBundle.getString("MYSQL_MIN_IDLE"));
    private static final int MYSQL_MAX_WAIT_MILLIS = Integer.parseInt(resourceBundle.getString("MYSQL_MAX_WAIT_MILLIS"));
    //Redis配置获取
    private static final String REDIS_HOST = resourceBundle.getString("REDIS_HOST");
    private static final int REDIS_PORT = Integer.parseInt(resourceBundle.getString("REDIS_PORT"));
    private static final String REDIS_PASSWORD = resourceBundle.getString("REDIS_PASSWORD");
    private static final int REDIS_MAX_TOTAL = Integer.parseInt(resourceBundle.getString("REDIS_MAX_TOTAL"));
    private static final int REDIS_MAX_IDLE = Integer.parseInt(resourceBundle.getString("REDIS_MAX_IDLE"));
    private static final int REDIS_MIN_IDLE = Integer.parseInt(resourceBundle.getString("REDIS_MIN_IDLE"));
    private static final int REDIS_MAX_WAIT_MILLIS = Integer.parseInt(resourceBundle.getString("REDIS_MAX_WAIT_MILLIS"));
    //主机路径获取
    private static final String WIFI_AUTH_HOST = resourceBundle.getString("WIFI_AUTH_HOST");
    private static final String WIFI_MQ_HOST = resourceBundle.getString("WIFI_MQ_HOST");
    //Redis库配置获取
    private static final int TABLE_BINDING_INFO_INDEX = Integer.parseInt(resourceBundle.getString("TABLE_BINDING_INFO_INDEX"));
    private static final int TABLE_BINDING_INFO_SURVIVAL_TIME = Integer.parseInt(resourceBundle.getString("TABLE_BINDING_INFO_SURVIVAL_TIME"));
    private static final int TABLE_AREA_INFO_INDEX = Integer.parseInt(resourceBundle.getString("TABLE_AREA_INFO_INDEX"));
    private static final int TABLE_AREA_INFO_SURVIVAL_TIME = Integer.parseInt(resourceBundle.getString("TABLE_AREA_INFO_SURVIVAL_TIME"));
    private static final int TABLE_USER_INFO_INDEX = Integer.parseInt(resourceBundle.getString("TABLE_USER_INFO_INDEX"));
    private static final int TABLE_USER_INFO_SURVIVAL_TIME = Integer.parseInt(resourceBundle.getString("TABLE_USER_INFO_SURVIVAL_TIME"));
    private static final int TABLE_COMMAND_INDEX = Integer.parseInt(resourceBundle.getString("TABLE_COMMAND_INDEX"));
    private static final int TABLE_COMMAND_SURVIVAL_TIME = Integer.parseInt(resourceBundle.getString("TABLE_COMMAND_SURVIVAL_TIME"));

    public static String getMysqlHost() {
        return MYSQL_HOST;
    }

    public static int getMysqlPort() {
        return MYSQL_PORT;
    }

    public static String getMysqlDbName() {
        return MYSQL_DB_NAME;
    }

    public static String getMysqlUsername() {
        return MYSQL_USERNAME;
    }

    public static String getMysqlPassword() {
        return MYSQL_PASSWORD;
    }

    public static int getMysqlInitialSize() {
        return MYSQL_INITIAL_SIZE;
    }

    public static int getMysqlMaxTotal() {
        return MYSQL_MAX_TOTAL;
    }

    public static int getMysqlMaxIdle() {
        return MYSQL_MAX_IDLE;
    }

    public static int getMysqlMinIdle() {
        return MYSQL_MIN_IDLE;
    }

    public static int getMysqlMaxWaitMillis() {
        return MYSQL_MAX_WAIT_MILLIS;
    }

    public static String getRedisHost() {
        return REDIS_HOST;
    }

    public static int getRedisPort() {
        return REDIS_PORT;
    }

    public static String getRedisPassword() {
        return REDIS_PASSWORD;
    }

    public static int getRedisMaxTotal() {
        return REDIS_MAX_TOTAL;
    }

    public static int getRedisMaxIdle() {
        return REDIS_MAX_IDLE;
    }

    public static int getRedisMinIdle() {
        return REDIS_MIN_IDLE;
    }

    public static int getRedisMaxWaitMillis() {
        return REDIS_MAX_WAIT_MILLIS;
    }

    public static String getWifiAuthHost() {
        return WIFI_AUTH_HOST;
    }

    public static String getWifiMqHost() {
        return WIFI_MQ_HOST;
    }

    public static int getTableBindingInfoIndex() {
        return TABLE_BINDING_INFO_INDEX;
    }

    public static int getTableBindingInfoSurvivalTime() {
        return TABLE_BINDING_INFO_SURVIVAL_TIME;
    }

    public static int getTableAreaInfoIndex() {
        return TABLE_AREA_INFO_INDEX;
    }

    public static int getTableAreaInfoSurvivalTime() {
        return TABLE_AREA_INFO_SURVIVAL_TIME;
    }

    public static int getTableUserInfoIndex() {
        return TABLE_USER_INFO_INDEX;
    }

    public static int getTableUserInfoSurvivalTime() {
        return TABLE_USER_INFO_SURVIVAL_TIME;
    }

    public static int getTableCommandIndex() {
        return TABLE_COMMAND_INDEX;
    }

    public static int getTableCommandSurvivalTime() {
        return TABLE_COMMAND_SURVIVAL_TIME;
    }
}
