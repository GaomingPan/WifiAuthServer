package cn.com.octodata.auth.model;

/**
 * Created by aran on 16-3-4.
 */
public class DeviceSettings {
    private int disable;//无线功能开关 0:开启;1:关闭
    private String ssid;//SSID
    private int ssidHidden;//是否隐藏SSID  0:显示;1:隐藏.
    private String encryption;//加密方式 none|psk|psk2
    private String key;//密码
    private String ipAddress;    //网关IP地址
    private String ipMask;    //子网掩码
    private int ignore;//是否启用DHCP 0:启用DHCP;1:关闭DHCP
    private int start;//DHCP开始
    private int limit;//DHCP容量
    private String leaseTime;//IP地址租期 12m 十二分钟
    private int autoReboot;//是否自动重启
    private String rebootTime;//重启时间 24h制时间 hh:mm

    public DeviceSettings() {
    }

    public DeviceSettings(int disable, String ssid, int ssidHidden, String encryption, String key, String ipAddress, String ipMask, int ignore, int start, int limit, String leaseTime, int autoReboot, String rebootTime) {
        this.disable = disable;
        this.ssid = ssid;
        this.ssidHidden = ssidHidden;
        this.encryption = encryption;
        this.key = key;
        this.ipAddress = ipAddress;
        this.ipMask = ipMask;
        this.ignore = ignore;
        this.start = start;
        this.limit = limit;
        this.leaseTime = leaseTime;
        this.autoReboot = autoReboot;
        this.rebootTime = rebootTime;
    }

    public int getDisable() {
        return disable;
    }

    public void setDisable(int disable) {
        this.disable = disable;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public int getSsidHidden() {
        return ssidHidden;
    }

    public void setSsidHidden(int ssidHidden) {
        this.ssidHidden = ssidHidden;
    }

    public String getEncryption() {
        return encryption;
    }

    public void setEncryption(String encryption) {
        this.encryption = encryption;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpMask() {
        return ipMask;
    }

    public void setIpMask(String ipMask) {
        this.ipMask = ipMask;
    }

    public int getIgnore() {
        return ignore;
    }

    public void setIgnore(int ignore) {
        this.ignore = ignore;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getLeaseTime() {
        return leaseTime;
    }

    public void setLeaseTime(String leaseTime) {
        this.leaseTime = leaseTime;
    }

    public int getAutoReboot() {
        return autoReboot;
    }

    public void setAutoReboot(int autoReboot) {
        this.autoReboot = autoReboot;
    }

    public String getRebootTime() {
        return rebootTime;
    }

    public void setRebootTime(String rebootTime) {
        this.rebootTime = rebootTime;
    }
}
