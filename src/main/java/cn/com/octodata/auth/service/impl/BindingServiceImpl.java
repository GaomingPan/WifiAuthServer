package cn.com.octodata.auth.service.impl;

import cn.com.octodata.auth.dao.BindingDao;
import cn.com.octodata.auth.service.BindingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by aran on 16-2-29.
 */
@Service
public class BindingServiceImpl implements BindingService {
    @Autowired
    BindingDao bindingDao;

    @Override
    public String set(String deviceID, String areaID) {
        return bindingDao.set(deviceID, areaID);
    }

    @Override
    public long del(String deviceID) {
        return bindingDao.del(deviceID);
    }

    @Override
    public String get(String deviceID) throws Exception {
        return bindingDao.get(deviceID);
    }
}
