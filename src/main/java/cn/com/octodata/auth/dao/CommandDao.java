package cn.com.octodata.auth.dao;

/**
 * Created by aran on 16-2-24.
 */
public interface CommandDao {
    long rPush(String deviceID, String commandString);

    long del(String deviceID);

    String lPop(String deviceID);
}
