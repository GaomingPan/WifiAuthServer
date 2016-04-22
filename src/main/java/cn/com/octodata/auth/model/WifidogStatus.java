package cn.com.octodata.auth.model;

import java.util.List;

/**
 * wifidog状态
 * Created by aran on 16-2-29.
 */
public class WifidogStatus {
    private String version;//wifidog版本号
    private String uptime;//wifidog在线时长
    private int clients_served_session;//客户端认证次数
    private int client_counter;//客户端在线人数
    private List<String> clients;//客户端认证mac地址
    private AuthenticationServer authentication_server;//认服证务器信息

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public int getClients_served_session() {
        return clients_served_session;
    }

    public void setClients_served_session(int clients_served_session) {
        this.clients_served_session = clients_served_session;
    }

    public int getClient_counter() {
        return client_counter;
    }

    public void setClient_counter(int client_counter) {
        this.client_counter = client_counter;
    }

    public List<String> getClients() {
        return clients;
    }

    public void setClients(List<String> clients) {
        this.clients = clients;
    }

    public AuthenticationServer getAuthentication_server() {
        return authentication_server;
    }

    public void setAuthentication_server(AuthenticationServer authentication_server) {
        this.authentication_server = authentication_server;
    }
}
