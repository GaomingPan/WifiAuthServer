package cn.com.octodata.auth.model;

/**
 * Created by aran on 16-2-24.
 */
public class UserAuthInfo {
    private long loginTime;
    private long offlineTime;
    private long backupTime;

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public long getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(long offlineTime) {
        this.offlineTime = offlineTime;
    }

    public long getBackupTime() {
        return backupTime;
    }

    public void setBackupTime(long backupTime) {
        this.backupTime = backupTime;
    }
}
