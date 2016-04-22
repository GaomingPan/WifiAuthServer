package cn.com.octodata.auth.dao;

import cn.com.octodata.auth.model.Device;
import cn.com.octodata.auth.model.DeviceSettings;

import java.util.List;

/**
 * Created by aran on 16-2-24.
 */
public interface DeviceDao extends BaseDao<Device, String> {
    int updateAreaId(String deviceId, String areaId) throws Exception;

    List<String> selectDeviceList(String areaId) throws Exception;

    DeviceSettings selectDeviceSettings(String deviceId) throws Exception;
}
