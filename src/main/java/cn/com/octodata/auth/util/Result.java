package cn.com.octodata.auth.util;

/**
 * Created by aran on 16-2-25.
 */
public class Result {
    private boolean status;
    private Object data;

    public Result(boolean status, Object data) {
        this.status = status;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
