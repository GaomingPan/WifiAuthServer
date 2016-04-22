package cn.com.octodata.auth.service;

import cn.com.octodata.auth.model.UserAuthInfo;

/**
 * Created by aran on 16-2-25.
 */
public interface UserAuthInfoService {
    long hSet(String areaId, String userMac, String userAuthInfoJsonString);

    long hDel(String areaId, String userMac);

    String hGet(String areaId, String userMac);

    long hSet(String areaId, String userMac, UserAuthInfo userAuthInfo);

    UserAuthInfo hGetUserAuthInfo(String areaId, String userMac);
}
