package cn.com.octodata.auth.service;

/**
 * Created by aran on 16-2-29.
 */
public interface BindingService {
    String set(String deviceID, String areaID);

    long del(String deviceID);

    String get(String deviceID) throws Exception;
}
