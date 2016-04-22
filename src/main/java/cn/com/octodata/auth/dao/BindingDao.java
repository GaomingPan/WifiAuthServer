package cn.com.octodata.auth.dao;

/**
 * Created by aran on 16-2-24.
 */
public interface BindingDao {
    String set(String deviceID, String areaID);

    long del(String deviceID);

    String get(String deviceID) throws Exception;
}
