package cn.com.octodata.auth.service.impl;

import cn.com.octodata.auth.dao.CommandDao;
import cn.com.octodata.auth.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by aran on 16-2-25.
 */
@Service
public class CommandServiceImpl implements CommandService {
    @Autowired
    CommandDao commandDao;

    @Override
    public long rPush(String deviceID, String commandString) {
        return commandDao.rPush(deviceID, commandString);
    }

    @Override
    public long del(String deviceID) {
        return commandDao.del(deviceID);
    }

    @Override
    public String lPop(String deviceID) {
        return commandDao.lPop(deviceID);
    }
}
