package cn.com.octodata.auth.service.impl;

import cn.com.octodata.auth.dao.AreaDao;
import cn.com.octodata.auth.dao.CommandDao;
import cn.com.octodata.auth.dao.DeviceDao;
import cn.com.octodata.auth.dao.UserAuthInfoDao;
import cn.com.octodata.auth.model.Area;
import cn.com.octodata.auth.model.AreaInfo;
import cn.com.octodata.auth.model.BlackAndWhiteList;
import cn.com.octodata.auth.model.UserAuthInfo;
import cn.com.octodata.auth.service.AreaService;
import cn.com.octodata.auth.util.Result;
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by aran on 16-2-24.
 */
@Service
public class AreaServiceImpl extends BaseServiceImpl<Area, String> implements AreaService {

    private static final Log LOG = LogFactory.getLog(AreaServiceImpl.class);

    @Autowired
    AreaDao areaDao;

    @Autowired
    DeviceDao deviceDao;

    @Autowired
    CommandDao commandDao;

    @Autowired
    UserAuthInfoDao userAuthInfoDao;

    /**
     * 插入一条数据
     *
     * @param area 欲插入的数据
     * @return 改变的行数
     * @throws Exception
     */
    @Override
    public int insert(Area area) throws Exception {
        return areaDao.insert(area);
    }

    /**
     * 删除一条数据
     *
     * @param s 欲删除数据的主键
     * @return 改变的行数
     * @throws Exception
     */
    @Override
    public int delete(String s) throws Exception {
        return areaDao.delete(s);
    }

    /**
     * 更新一条数据
     *
     * @param area 欲更新的数据
     * @return 更改的行数
     * @throws Exception
     */
    @Override
    public int update(Area area) throws Exception {
        return areaDao.update(area);
    }

    /**
     * 查询一条数据
     *
     * @param s 欲查询数据的主键
     * @return 查询得到的结果
     * @throws Exception
     */
    @Override
    public Area select(String s) throws Exception {
        return areaDao.select(s);
    }

    /**
     * 查询全表数据
     *
     * @return 全表数据的列表
     * @throws Exception
     */
    @Override
    public List<Area> selectAll() throws Exception {
        return areaDao.selectAll();
    }

    /**
     * 数据计数
     *
     * @return 所有数据的计数
     * @throws Exception
     */
    @Override
    public int countAll() throws Exception {
        return areaDao.countAll();
    }

    @Override
    public int updateAreaInfoJsonString(String areaId, String areaInfoJsonString) throws Exception {
        return areaDao.updateAreaInfoJsonString(areaId, areaInfoJsonString);
    }

    @Override
    public int updateBlackAndWhiteList(String areaId, BlackAndWhiteList blackAndWhiteList) throws Exception {
        return areaDao.updateBlackAndWhiteList(areaId, blackAndWhiteList);
    }

    @Override
    public String selectAreaInfoJsonString(String areaId) throws Exception {
        return areaDao.selectAreaInfoJsonString(areaId);
    }

    @Override
    public BlackAndWhiteList selectBlackAndWhiteList(String areaId) throws Exception {
        return areaDao.selectBlackAndWhiteList(areaId);
    }

    @Override
    public String add(Area area) {
        try {
            if (areaDao.insert(area) == 1) {
                return JSON.toJSONString(new Result(true, true));
            } else {
                return JSON.toJSONString(new Result(true, false));
            }
        } catch (Exception e) {
            LOG.warn(e);
            return JSON.toJSONString(new Result(false, e.getMessage()));
        }
    }

    @Override
    public String del(String areaId) {
        try {
            if (areaDao.delete(areaId) == 1) {
                return JSON.toJSONString(new Result(true, true));
            } else {
                return JSON.toJSONString(new Result(true, false));
            }
        } catch (Exception e) {
            LOG.warn(e);
            return JSON.toJSONString(new Result(false, e.getMessage()));
        }
    }

    @Override
    public String set(String areaJsonString) {
        try {
            if (areaDao.update(JSON.parseObject(areaJsonString, Area.class)) == 1) {
                return JSON.toJSONString(new Result(true, true));
            } else {
                return JSON.toJSONString(new Result(true, false));
            }
        } catch (Exception e) {
            LOG.warn(e);
            return JSON.toJSONString(new Result(false, e.getMessage()));
        }
    }

    @Override
    public String get(String areaId) {
        try {
            return JSON.toJSONString(new Result(true, areaDao.select(areaId)));
        } catch (Exception e) {
            LOG.warn(e);
            return JSON.toJSONString(new Result(false, e.getMessage()));
        }
    }

    @Override
    public String setInfo(String areaId, String areaInfoJsonString) {
        try {
            if (areaDao.updateAreaInfoJsonString(areaId, areaInfoJsonString) != 0) {
                return JSON.toJSONString(new Result(true, true));
            } else {
                return JSON.toJSONString(new Result(true, false));
            }
        } catch (Exception e) {
            LOG.warn(e);
            return JSON.toJSONString(new Result(false, e.getMessage()));
        }
    }

    @Override
    public String getInfo(String areaId) {
        try {
            return JSON.toJSONString(new Result(true, JSON.parseObject(areaDao.selectAreaInfoJsonString(areaId), AreaInfo.class)));
        } catch (Exception e) {
            LOG.warn(e);
            return JSON.toJSONString(new Result(false, e.getMessage()));
        }
    }

    @Override
    public String setList(String areaId, String blackAndWhiteListJsonString) {
        try {
            BlackAndWhiteList blackAndWhiteList = JSON.parseObject(blackAndWhiteListJsonString, BlackAndWhiteList.class);
            if (areaDao.updateBlackAndWhiteList(areaId, blackAndWhiteList) != 0) {
                List<String> deviceIdList = deviceDao.selectDeviceList(areaId);
                blackAndWhiteList.setBlackList(blackAndWhiteList.getBlackList().equals("") ? "NULL" : blackAndWhiteList.getBlackList());
                blackAndWhiteList.setWhiteList(blackAndWhiteList.getWhiteList().equals("") ? "NULL" : blackAndWhiteList.getWhiteList());
                for (String deviceId : deviceIdList) {
                    commandDao.rPush(deviceId, "Pong | 5 | accmd_mac_set_white_black 5 " + deviceId + " 1 \"" + blackAndWhiteList.getBlackList() + "\" \"" + blackAndWhiteList.getWhiteList() + "\"");
                }
                return JSON.toJSONString(new Result(true, true));
            } else {
                return JSON.toJSONString(new Result(true, false));
            }
        } catch (Exception e) {
            LOG.warn(e);
            return JSON.toJSONString(new Result(false, e.getMessage()));
        }
    }

    @Override
    public String getList(String areaId) {
        try {
            return JSON.toJSONString(new Result(true, areaDao.selectBlackAndWhiteList(areaId)));
        } catch (Exception e) {
            LOG.warn(e);
            return JSON.toJSONString(new Result(false, e.getMessage()));
        }
    }

    @Override
    public String getDeviceList(String areaId) {
        try {
            return JSON.toJSONString(new Result(true, deviceDao.selectDeviceList(areaId)));
        } catch (Exception e) {
            LOG.warn(e);
            return JSON.toJSONString(new Result(false, e.getMessage()));
        }
    }

    @Override
    public String offline(String areaId, String userMac) {
        try {
            UserAuthInfo userAuthInfo = userAuthInfoDao.hGetUserAuthInfo(areaId, userMac);
            userAuthInfo.setOfflineTime(System.currentTimeMillis());
            if (userAuthInfoDao.hSet(areaId, userMac, userAuthInfo) != 0) {
                return JSON.toJSONString(new Result(true, true));
            } else {
                return JSON.toJSONString(new Result(true, false));

            }
        } catch (Exception e) {
            LOG.warn(e);
            return JSON.toJSONString(new Result(false, e.getMessage()));
        }
    }
}
