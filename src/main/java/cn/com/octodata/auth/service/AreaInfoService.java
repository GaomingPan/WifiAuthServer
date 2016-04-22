package cn.com.octodata.auth.service;

import cn.com.octodata.auth.model.AreaInfo;

/**
 * Created by aran on 16-2-25.
 */
public interface AreaInfoService {
    String set(String areaID, String areaInfoJsonString);

    long del(String areaID);

    String get(String areaID) throws Exception;

    AreaInfo getAreaInfo(String areaID) throws Exception;
}
