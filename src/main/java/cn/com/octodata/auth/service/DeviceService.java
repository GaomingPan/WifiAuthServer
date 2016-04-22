package cn.com.octodata.auth.service;

import cn.com.octodata.auth.model.Device;
import cn.com.octodata.auth.model.DeviceSettings;

import java.util.List;

/**
 * Created by aran on 16-2-24.
 */
public interface DeviceService extends BaseService<Device, String> {
    int updateAreaId(String deviceId, String areaId) throws Exception;

    List<String> selectDeviceList(String areaId) throws Exception;

    DeviceSettings selectDeviceSettings(String deviceId) throws Exception;

    String add(Device device);

    String del(String deviceId);

    String set(String deviceJsonString);

    String get(String deviceId);

    String setBindingAreaId(String deviceId, String areaId);

    String setUnbindingAreaId(String deviceId);

    String setSettings(String deviceId, String settingsJsonString);

    String getSettings(String deviceId);

    String sync(String deviceId);

    String reboot(String deviceId);
}
