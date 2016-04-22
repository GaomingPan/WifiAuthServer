package cn.com.octodata.auth.model;

/**
 * 连接客户端
 * Created by aran on 16-2-24.
 */
public class Client {
    private String mac;//客户端mac地址
    private String ip;//客户端ip地址
    private long incoming;//客户端下载流量
    private long outgoing;//客户端上传流量
    private String hostname;//客户端主机名
    private long go_speed;//客户端上传速度
    private long come_speed;//客户端下载速度

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
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
}
