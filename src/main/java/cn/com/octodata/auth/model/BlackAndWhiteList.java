package cn.com.octodata.auth.model;

/**
 * Created by aran on 16-3-2.
 */
public class BlackAndWhiteList {
    String blackList;
    String whiteList;

    private BlackAndWhiteList() {
    }

    public BlackAndWhiteList(String blackList, String whiteList) {
        this.blackList = blackList;
        this.whiteList = whiteList;
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
