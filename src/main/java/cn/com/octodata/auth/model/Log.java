package cn.com.octodata.auth.model;

/**
 * Created by aran on 16-2-24.
 */
public class Log {
    String deviceID;
    String userMAC;
    String message;

    public Log(String deviceID, String userMAC, String message) {
        this.deviceID = deviceID;
        this.userMAC = userMAC;
        this.message = message;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getUserMAC() {
        return userMAC;
    }

    public void setUserMAC(String userMAC) {
        this.userMAC = userMAC;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
