package cn.com.octodata.auth.service.impl;

import cn.com.octodata.auth.dao.*;
import cn.com.octodata.auth.model.BlackAndWhiteList;
import cn.com.octodata.auth.model.Device;
import cn.com.octodata.auth.model.DeviceSettings;
import cn.com.octodata.auth.model.UserAuthInfo;
import cn.com.octodata.auth.service.AccessService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by aran on 16-2-29.
 */
@Service
public class AccessServiceImpl implements AccessService {
    @Autowired
    UserAuthInfoDao userAuthInfoDao;

    @Autowired
    BindingDao bindingDao;

    @Autowired
    CommandDao commandDao;

    @Autowired
    DeviceDao deviceDao;

    @Autowired
    AreaDao areaDao;

    @Override
    public String getCommandString(String deviceId) {
        return commandDao.lPop(deviceId);
    }

    @Override
    public String offlineClients(String deviceId, List<String> clients) throws Exception {
        String areaId = bindingDao.get(deviceId);
        long nowTimeMillis = System.currentTimeMillis();
        String offlineClientString = "";
        for (String client : clients) {
            UserAuthInfo userAuthInfo = userAuthInfoDao.hGetUserAuthInfo(areaId, client);
            if (userAuthInfo == null || userAuthInfo.getOfflineTime() < nowTimeMillis) {
                offlineClientString += client + " ";
            }
        }
        if (offlineClientString.isEmpty()) {
            return null;
        }
        return "Pong | 0 | accmd_updatelist 0 " + deviceId + " \"" + offlineClientString + "\"";
    }

    @Override
    public boolean updateSync(String deviceId, String settingJsonString) throws Exception {
        Device device = deviceDao.select(deviceId);
        DeviceSettings deviceSettings = JSON.parseObject(settingJsonString, DeviceSettings.class);
        device.setDisable(deviceSettings.getDisable());
        device.setSsid(deviceSettings.getSsid());
        device.setSsidHidden(deviceSettings.getSsidHidden());
        device.setEncryption(deviceSettings.getEncryption());
        device.setKey(deviceSettings.getKey());
        device.setIgnore(deviceSettings.getIgnore());
        device.setStart(deviceSettings.getStart());
        device.setLimit(deviceSettings.getLimit());
        device.setLeaseTime(deviceSettings.getLeaseTime());
        device.setIpAddress(deviceSettings.getIpAddress());
        device.setIpMask(deviceSettings.getIpMask());
        device.setAutoReboot(deviceSettings.getAutoReboot());
        device.setRebootTime(deviceSettings.getRebootTime());
        String areaId = bindingDao.get(deviceId);

        BlackAndWhiteList blackAndWhiteList = areaDao.selectBlackAndWhiteList(areaId);
        blackAndWhiteList.setBlackList(blackAndWhiteList.getBlackList() == null ? "NULL" : blackAndWhiteList.getBlackList());
        blackAndWhiteList.setWhiteList(blackAndWhiteList.getWhiteList() == null ? "NULL" : blackAndWhiteList.getWhiteList());
        commandDao.rPush(deviceId, "Pong | 6 | accmd_mac_set_white_black 6 1 \"" + blackAndWhiteList.getBlackList() + "\" \"" + blackAndWhiteList.getWhiteList() + "\"");
        return deviceDao.update(device) == 1;
    }

    @Override
    public boolean updateWireless(String deviceId, String wirelessJsonString) throws Exception {
        Device device = deviceDao.select(deviceId);
        DeviceSettings deviceSettings = JSON.parseObject(wirelessJsonString, DeviceSettings.class);
        device.setDisable(deviceSettings.getDisable());
        device.setSsid(deviceSettings.getSsid());
        device.setSsidHidden(deviceSettings.getSsidHidden());
        device.setEncryption(deviceSettings.getEncryption());
        device.setKey(deviceSettings.getKey());
        return deviceDao.update(device) == 1;
    }

    @Override
    public boolean updateDHCP(String deviceId, String dHCPJsonString) throws Exception {
        Device device = deviceDao.select(deviceId);
        DeviceSettings deviceSettings = JSON.parseObject(dHCPJsonString, DeviceSettings.class);
        device.setIgnore(deviceSettings.getIgnore());
        device.setStart(deviceSettings.getStart());
        device.setLimit(deviceSettings.getLimit());
        device.setLeaseTime(deviceSettings.getLeaseTime());
        return deviceDao.update(device) == 1;
    }

    @Override
    public boolean updateNetwork(String deviceId, String networkJsonString) throws Exception {
        Device device = deviceDao.select(deviceId);
        DeviceSettings deviceSettings = JSON.parseObject(networkJsonString, DeviceSettings.class);
        device.setIpAddress(deviceSettings.getIpAddress());
        device.setIpMask(deviceSettings.getIpMask());
        return deviceDao.update(device) == 1;
    }

    @Override
    public boolean updateRebootTime(String deviceId, String rebootTimeJsonString) throws Exception {
        Device device = deviceDao.select(deviceId);
        if (rebootTimeJsonString == null) {
            device.setAutoReboot(0);
        } else {
            DeviceSettings deviceSettings = JSON.parseObject(rebootTimeJsonString, DeviceSettings.class);
            device.setAutoReboot(1);
            device.setRebootTime(deviceSettings.getRebootTime());
        }
        return deviceDao.update(device) == 1;
    }
}
