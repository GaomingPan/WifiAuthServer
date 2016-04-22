package cn.com.octodata.auth.dao;

import cn.com.octodata.auth.model.UserAuthInfo;

/**
 * Created by aran on 16-2-24.
 */
public interface UserAuthInfoDao {
    long hSet(String areaId, String userMac, String userAuthInfoJsonString);

    long hDel(String areaId, String userMac);

    String hGet(String areaId, String userMac);

    long hSet(String areaId, String userMac, UserAuthInfo userAuthInfo);

    UserAuthInfo hGetUserAuthInfo(String areaId, String userMac);
}
