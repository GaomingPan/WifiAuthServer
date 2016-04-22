package cn.com.octodata.auth.model;

/**
 * 设备信息
 * Created by aran on 16-2-24.
 */
public class DeviceData {
    private String sys_uptime;    // 系统启动时长
    private String sys_memfree;   // 系统空闲内存
    private double sys_load;      // 系统负载
    private String gw_mac;        // 网关mac
    private String gw_ssid;       // 网关ssid
    private int cpu_use;          // CPU使用率
    private String ac_version;    // ac版本号
    private String wan_ip;        // wan接口ip
    private long go_speed;        // 上传速率
    private long come_speed;      // 下载速率
    private long incoming;        // 下载流量
    private long outgoing;        // 上传流量
    private long timestamp;       // 检查网关id

    public DeviceData() {
        this.timestamp = System.currentTimeMillis();
    }

    public String getSys_uptime() {
        return sys_uptime;
    }

    public void setSys_uptime(String sys_uptime) {
        this.sys_uptime = sys_uptime;
    }

    public String getSys_memfree() {
        return sys_memfree;
    }

    public void setSys_memfree(String sys_memfree) {
        this.sys_memfree = sys_memfree;
    }

    public double getSys_load() {
        return sys_load;
    }

    public void setSys_load(double sys_load) {
        this.sys_load = sys_load;
    }

    public String getGw_mac() {
        return gw_mac;
    }

    public void setGw_mac(String gw_mac) {
        this.gw_mac = gw_mac;
    }

    public String getGw_ssid() {
        return gw_ssid;
    }

    public void setGw_ssid(String gw_ssid) {
        this.gw_ssid = gw_ssid;
    }

    public int getCpu_use() {
        return cpu_use;
    }

    public void setCpu_use(int cpu_use) {
        this.cpu_use = cpu_use;
    }

    public String getAc_version() {
        return ac_version;
    }

    public void setAc_version(String ac_version) {
        this.ac_version = ac_version;
    }

    public String getWan_ip() {
        return wan_ip;
    }

    public void setWan_ip(String wan_ip) {
        this.wan_ip = wan_ip;
    }

    public long getGo_speed() {
        return go_speed;
    }

    public void setGo_speed(long go_speed) {
        this.go_speed = go_speed;
    }

    public long getCome_speed() {
        return come_speed;
    }

    public void setCome_speed(long come_speed) {
        this.come_speed = come_speed;
    }

    public long getIncoming() {
        return incoming;
    }

    public void setIncoming(long incoming) {
        this.incoming = incoming;
    }

    public long getOutgoing() {
        return outgoing;
    }

    public void setOutgoing(long outgoing) {
        this.outgoing = outgoing;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
