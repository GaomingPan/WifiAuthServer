package cn.com.octodata.auth.dao;

import cn.com.octodata.auth.model.Area;
import cn.com.octodata.auth.model.BlackAndWhiteList;

/**
 * Created by aran on 16-2-24.
 */
public interface AreaDao extends BaseDao<Area, String> {
    int updateAreaInfoJsonString(String areaId, String areaInfoJsonString) throws Exception;

    int updateBlackAndWhiteList(String areaId, BlackAndWhiteList blackAndWhiteList) throws Exception;

    String selectAreaInfoJsonString(String areaId) throws Exception;

    BlackAndWhiteList selectBlackAndWhiteList(String areaId) throws Exception;
}
