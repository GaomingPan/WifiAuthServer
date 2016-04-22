package cn.com.octodata.auth.model;

/**
 * 认服证务器信息
 * Created by aran on 16-2-29.
 */
public class AuthenticationServer {
    private String host;//认证服务器主机名
    private String ip;//认证服务起ip地址

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
