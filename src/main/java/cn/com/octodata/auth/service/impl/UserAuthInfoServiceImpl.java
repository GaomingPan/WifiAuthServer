package cn.com.octodata.auth.service.impl;

import cn.com.octodata.auth.dao.UserAuthInfoDao;
import cn.com.octodata.auth.model.UserAuthInfo;
import cn.com.octodata.auth.service.UserAuthInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by aran on 16-2-25.
 */
@Service
public class UserAuthInfoServiceImpl implements UserAuthInfoService {
    @Autowired
    UserAuthInfoDao userAuthInfoDao;

    @Override
    public long hSet(String areaId, String userMac, String userAuthInfoJsonString) {
        return userAuthInfoDao.hSet(areaId, userMac, userAuthInfoJsonString);
    }

    @Override
    public long hDel(String areaId, String userMac) {
        return userAuthInfoDao.hDel(areaId, userMac);
    }

    @Override
    public String hGet(String areaId, String userMac) {
        return userAuthInfoDao.hGet(areaId, userMac);
    }

    @Override
    public long hSet(String areaId, String userMac, UserAuthInfo userAuthInfo) {
        return userAuthInfoDao.hSet(areaId, userMac, userAuthInfo);
    }

    @Override
    public UserAuthInfo hGetUserAuthInfo(String areaId, String userMac) {
        return userAuthInfoDao.hGetUserAuthInfo(areaId, userMac);
    }
}
