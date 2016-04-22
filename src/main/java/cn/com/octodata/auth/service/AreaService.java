package cn.com.octodata.auth.service;

import cn.com.octodata.auth.model.Area;
import cn.com.octodata.auth.model.BlackAndWhiteList;

/**
 * Created by aran on 16-2-24.
 */
public interface AreaService extends BaseService<Area, String> {
    int updateAreaInfoJsonString(String areaId, String areaInfoJsonString) throws Exception;

    int updateBlackAndWhiteList(String areaId, BlackAndWhiteList blackAndWhiteList) throws Exception;

    String selectAreaInfoJsonString(String areaId) throws Exception;

    BlackAndWhiteList selectBlackAndWhiteList(String areaId) throws Exception;

    String add(Area area);

    String del(String areaId);

    String set(String areaJsonString);

    String get(String areaId);

    String setInfo(String areaId, String areaInfoJsonString);

    String getInfo(String areaId);

    String setList(String areaId, String blackAndWhiteListJsonString);

    String getList(String areaId);

    String getDeviceList(String areaId);

    String offline(String areaId, String userMac);
}
