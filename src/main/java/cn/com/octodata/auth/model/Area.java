package cn.com.octodata.auth.model;

import com.alibaba.fastjson.JSON;

/**
 * Created by aran on 16-2-24.
 */
public class Area {
    private String areaID;
    private String areaInfoJsonString;
    private String blackList;
    private String whiteList;

    public Area() {
    }

    public Area(String areaID) {
        this.areaID = areaID;
        this.areaInfoJsonString = JSON.toJSONString(new AreaInfo());
        this.blackList = "";
        this.whiteList = "";
    }

    public Area(String areaID, String areaInfoJsonString, String blackList, String whiteList) {
        this.areaID = areaID;
        this.areaInfoJsonString = areaInfoJsonString;
        this.blackList = blackList;
        this.whiteList = whiteList;
    }

    public String getAreaID() {
        return areaID;
    }

    public void setAreaID(String areaID) {
        this.areaID = areaID;
    }

    public String getAreaInfoJsonString() {
        return areaInfoJsonString;
    }

    public void setAreaInfoJsonString(String areaInfoJsonString) {
        this.areaInfoJsonString = areaInfoJsonString;
    }

    public String getBlackList() {
        return blackList;
    }

    public void setBlackList(String blackList) {
        this.blackList = blackList;
    }

    public String getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(String whiteList) {
        this.whiteList = whiteList;
    }
}
