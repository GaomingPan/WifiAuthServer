package cn.com.octodata.auth.service;

/**
 * Created by aran on 16-2-25.
 */
public interface CommandService {
    long rPush(String deviceID, String commandString);

    long del(String deviceID);

    String lPop(String deviceID);
}
