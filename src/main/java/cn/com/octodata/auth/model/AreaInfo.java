package cn.com.octodata.auth.model;

/**
 * Created by aran on 16-2-24.
 */
public class AreaInfo {
    private int authType;              // 认证类型
    private int limitTime;             // 单次认证允许上线时长
    private String title;              // 认证页面的标题
    private String backgroundUrl;      // 认证页面背景图片的URL
    private String buttonUrl;          // 认证页面按钮图片的URL
    private String successUrl;         // 一键认证方式认证成功后的跳转的成功页的URL
    private String appId;              // 微信关注认证方式的appId
    private String shopId;             // 微信关注认证方式的shopId
    private String secretKey;          // 微信关注认证方式的secretKey

    public AreaInfo() {
        this.authType = 2;
        this.limitTime = 3600000;
        this.title = "八爪WiFi";
        this.backgroundUrl = "http://bazhuawangluo.oss-cn-hangzhou.aliyuncs.com/com/wifimanager/ShopAuthImg/1458610707034.jpg";
        this.buttonUrl = "http://bazhuawangluo.oss-cn-hangzhou.aliyuncs.com/com/wifimanager/ShopAuthImg/1458610712828.jpg";
        this.successUrl = "http://www.baidu.com/";
        this.appId = "wxfefe846f71c6b559";
        this.shopId = "7569457";
        this.secretKey = "0cca8b28cba2e74b675a48f72be130e8";
    }

    public int getAuthType() {
        return authType;
    }

    public void setAuthType(int authType) {
        this.authType = authType;
    }

    public int getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(int limitTime) {
        this.limitTime = limitTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public String getButtonUrl() {
        return buttonUrl;
    }

    public void setButtonUrl(String buttonUrl) {
        this.buttonUrl = buttonUrl;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
