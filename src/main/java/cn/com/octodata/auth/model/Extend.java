package cn.com.octodata.auth.model;

/**
 * Created by aran on 16-2-24.
 */
public class Extend {
    private String areaId;
    private String userMac;

    private Extend() {
    }

    public Extend(String areaId, String userMac) {
        this.areaId = areaId;
        this.userMac = userMac;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getUserMac() {
        return userMac;
    }

    public void setUserMac(String userMac) {
        this.userMac = userMac;
    }
}
