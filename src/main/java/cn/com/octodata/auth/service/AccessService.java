package cn.com.octodata.auth.service;

import java.util.List;

/**
 * Created by aran on 16-2-29.
 */
public interface AccessService {
    String getCommandString(String deviceId);

    String offlineClients(String deviceId, List<String> clients) throws Exception;

    boolean updateSync(String deviceId, String settingJsonString) throws Exception;

    boolean updateWireless(String deviceId, String wirelessJsonString) throws Exception;

    boolean updateDHCP(String deviceId, String dHCPJsonString) throws Exception;

    boolean updateNetwork(String deviceId, String networkJsonString) throws Exception;

    boolean updateRebootTime(String deviceId, String rebootTimeJsonString) throws Exception;
}
