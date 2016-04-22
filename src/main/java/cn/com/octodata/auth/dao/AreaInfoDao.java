package cn.com.octodata.auth.dao;

import cn.com.octodata.auth.model.AreaInfo;

/**
 * Created by aran on 16-2-24.
 */
public interface AreaInfoDao {
    String set(String areaID, String areaInfoJsonString);

    long del(String areaID);

    String get(String areaID) throws Exception;

    AreaInfo getAreaInfo(String areaID) throws Exception;
}
