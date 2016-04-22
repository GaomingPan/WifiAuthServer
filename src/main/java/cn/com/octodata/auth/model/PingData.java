package cn.com.octodata.auth.model;

import java.util.List;

/**
 * PingData
 * Created by aran on 16-2-24.
 */
public class PingData {

    private String areaId;                // 区域Id
    private String gw_id;                 // 检查网关id
    private boolean wifidog_flag;         // 是否有 wifidog
    private DeviceData deviceData;        // 设备信息
    private List<Client> clientList;      // 连接客户端列表
    private WifidogStatus wifidog_status; // wifidog状态

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getGw_id() {
        return gw_id;
    }

    public void setGw_id(String gw_id) {
        this.gw_id = gw_id;
    }

    public boolean isWifidog_flag() {
        return wifidog_flag;
    }

    public void setWifidog_flag(boolean wifidog_flag) {
        this.wifidog_flag = wifidog_flag;
    }

    public DeviceData getDeviceData() {
        return deviceData;
    }

    public void setDeviceData(DeviceData deviceData) {
        this.deviceData = deviceData;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public WifidogStatus getWifidog_status() {
        return wifidog_status;
    }

    public void setWifidog_status(WifidogStatus wifidog_status) {
        this.wifidog_status = wifidog_status;
    }
}
