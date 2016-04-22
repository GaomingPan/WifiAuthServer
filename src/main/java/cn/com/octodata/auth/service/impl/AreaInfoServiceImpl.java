package cn.com.octodata.auth.service.impl;

import cn.com.octodata.auth.dao.AreaInfoDao;
import cn.com.octodata.auth.model.AreaInfo;
import cn.com.octodata.auth.service.AreaInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by aran on 16-2-25.
 */
@Service
public class AreaInfoServiceImpl implements AreaInfoService {
    @Autowired
    AreaInfoDao areaInfoDao;

    @Override
    public String set(String areaID, String areaInfoJsonString) {
        return areaInfoDao.set(areaID, areaInfoJsonString);
    }

    @Override
    public long del(String areaID) {
        return areaInfoDao.del(areaID);
    }

    @Override
    public String get(String areaID) throws Exception {
        return areaInfoDao.get(areaID);
    }

    @Override
    public AreaInfo getAreaInfo(String areaID) throws Exception {
        return areaInfoDao.getAreaInfo(areaID);
    }
}
