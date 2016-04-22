package cn.com.octodata.auth.model;

/**
 * Created by aran on 16-3-4.
 */
public class Output {
    int cmd_id;
    String gw_ac_id;
    boolean status;
    String info;
    String data;

    public int getCmd_id() {
        return cmd_id;
    }

    public void setCmd_id(int cmd_id) {
        this.cmd_id = cmd_id;
    }

    public String getGw_ac_id() {
        return gw_ac_id;
    }

    public void setGw_ac_id(String gw_ac_id) {
        this.gw_ac_id = gw_ac_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
