package cn.com.octodata.auth.service.impl;

import cn.com.octodata.auth.dao.AreaDao;
import cn.com.octodata.auth.dao.CommandDao;
import cn.com.octodata.auth.dao.DeviceDao;
import cn.com.octodata.auth.model.BlackAndWhiteList;
import cn.com.octodata.auth.model.Device;
import cn.com.octodata.auth.model.DeviceSettings;
import cn.com.octodata.auth.service.DeviceService;
import cn.com.octodata.auth.util.Config;
import cn.com.octodata.auth.util.JedisPool;
import cn.com.octodata.auth.util.Result;
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by aran on 16-2-24.
 */
@Service
public class DeviceServiceImpl extends BaseServiceImpl<Device, String> implements DeviceService {
    static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final Log LOG = LogFactory.getLog(DeviceServiceImpl.class);

    @Autowired
    DeviceDao deviceDao;
    @Autowired
    CommandDao commandDao;
    @Autowired
    AreaDao areaDao;

    /**
     * 插入一条数据
     *
     * @param device 欲插入的数据
     * @return 改变的行数
     * @throws Exception
     */
    @Override
    public int insert(Device device) throws Exception {
        return deviceDao.insert(device);
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
        return deviceDao.delete(s);
    }

    /**
     * 更新一条数据
     *
     * @param device 欲更新的数据
     * @return 更改的行数
     * @throws Exception
     */
    @Override
    public int update(Device device) throws Exception {
        return deviceDao.update(device);
    }

    /**
     * 查询一条数据
     *
     * @param s 欲查询数据的主键
     * @return 查询得到的结果
     * @throws Exception
     */
    @Override
    public Device select(String s) throws Exception {
        return deviceDao.select(s);
    }

    /**
     * 查询全表数据
     *
     * @return 全表数据的列表
     * @throws Exception
     */
    @Override
    public List<Device> selectAll() throws Exception {
        return deviceDao.selectAll();
    }

    /**
     * 数据计数
     *
     * @return 所有数据的计数
     * @throws Exception
     */
    @Override
    public int countAll() throws Exception {
        return deviceDao.countAll();
    }

    @Override
    public int updateAreaId(String deviceId, String areaId) throws Exception {
        return deviceDao.updateAreaId(deviceId, areaId);
    }

    @Override
    public List<String> selectDeviceList(String areaId) throws Exception {
        return deviceDao.selectDeviceList(areaId);
    }

    @Override
    public DeviceSettings selectDeviceSettings(String deviceId) throws Exception {
        return deviceDao.selectDeviceSettings(deviceId);
    }

    @Override
    public String add(Device device) {
        try {
            if (deviceDao.insert(device) == 1) {
                return JSON.toJSONString(new Result(true, true));
            } else {
                return JSON.toJSONString(new Result(false, false));
            }
        } catch (Exception e) {
            LOG.warn(e);
            return JSON.toJSONString(new Result(false, e.getMessage()));
        }
    }

    @Override
    public String del(String deviceId) {
        try {
            if (deviceDao.delete(deviceId) == 1) {
                return JSON.toJSONString(new Result(true, true));
            } else {
                return JSON.toJSONString(new Result(false, false));
            }
        } catch (Exception e) {
            LOG.warn(e);
            return JSON.toJSONString(new Result(false, e.getMessage()));
        }
    }

    @Override
    public String set(String deviceJsonString) {
        try {
            if (deviceDao.update(JSON.parseObject(deviceJsonString, Device.class)) == 1) {
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
    public String get(String deviceId) {
        try {
            System.out.println(JSON.toJSONString(new Result(true, deviceDao.select(deviceId))));
            return JSON.toJSONString(new Result(true, deviceDao.select(deviceId)));
        } catch (Exception e) {
            LOG.warn(e);
            return JSON.toJSONString(new Result(false, e.getMessage()));
        }
    }

    @Override
    public String setBindingAreaId(String deviceId, String areaId) {
        try {
            if (deviceDao.insert(new Device(deviceId, areaId)) == 1) {
                BlackAndWhiteList blackAndWhiteList = areaDao.selectBlackAndWhiteList(areaId);
                blackAndWhiteList.setBlackList(blackAndWhiteList.getBlackList() == null ? "NULL" : blackAndWhiteList.getBlackList());
                blackAndWhiteList.setWhiteList(blackAndWhiteList.getWhiteList() == null ? "NULL" : blackAndWhiteList.getWhiteList());
                commandDao.rPush(deviceId, "Pong | 6 | accmd_mac_set_white_black 6 1 \"" + blackAndWhiteList.getBlackList() + "\" \"" + blackAndWhiteList.getWhiteList() + "\"");
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
    public String setUnbindingAreaId(String deviceId) {
        try {
            if (deviceDao.delete(deviceId) == 1) {
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
    public String setSettings(String deviceId, String settingsJsonString) {
        try {

            DeviceSettings newDeviceSettings = JSON.parseObject(settingsJsonString, DeviceSettings.class);

            DeviceSettings oldDeviceSettings = deviceDao.selectDeviceSettings(deviceId);

            if (oldDeviceSettings.getDisable() != newDeviceSettings.getDisable() ||
                    !oldDeviceSettings.getSsid().equals(newDeviceSettings.getSsid()) ||
                    !oldDeviceSettings.getEncryption().equals(newDeviceSettings.getEncryption()) ||
                    !oldDeviceSettings.getKey().equals(newDeviceSettings.getKey()) ||
                    oldDeviceSettings.getSsidHidden() != newDeviceSettings.getSsidHidden()) {

                commandDao.rPush(deviceId, "Pong | 2 | accmd_setwireless 2 " + deviceId + " " + newDeviceSettings.getDisable() + " " + newDeviceSettings.getSsid() + " " + newDeviceSettings.getEncryption() + " " + newDeviceSettings.getKey() + " " + newDeviceSettings.getSsidHidden());
            }

            if (oldDeviceSettings.getStart() != newDeviceSettings.getStart() ||
                    oldDeviceSettings.getLimit() != newDeviceSettings.getLimit() ||
                    !oldDeviceSettings.getLeaseTime().equals(newDeviceSettings.getLeaseTime()) ||
                    oldDeviceSettings.getIgnore() != newDeviceSettings.getIgnore()) {

                commandDao.rPush(deviceId, "Pong | 3 | accmd_setdhcp 3 " + deviceId + " " + newDeviceSettings.getStart() + " " + newDeviceSettings.getLimit() + " " + newDeviceSettings.getLeaseTime() + " " + newDeviceSettings.getIgnore());
            }
            if (!oldDeviceSettings.getIpAddress().equals(newDeviceSettings.getIpAddress()) ||
                    !oldDeviceSettings.getIpMask().equals(newDeviceSettings.getIpMask())) {

                commandDao.rPush(deviceId, "Pong | 4 | accmd_setnetwork 4 " + deviceId + " lan static " + newDeviceSettings.getIpAddress() + " " + newDeviceSettings.getIpMask());
                Device device = deviceDao.select(deviceId);
                device.setIpAddress(newDeviceSettings.getIpAddress());
                device.setIpMask(newDeviceSettings.getIpMask());
                deviceDao.update(device);
            }
            if (oldDeviceSettings.getAutoReboot() != newDeviceSettings.getAutoReboot() ||
                    !oldDeviceSettings.getRebootTime().equals(newDeviceSettings.getRebootTime())) {
                if (newDeviceSettings.getAutoReboot() == 0) {
                    commandDao.rPush(deviceId, "Pong | 5 | accmd_setreboottime 5 " + deviceId);
                } else {
                    commandDao.rPush(deviceId, "Pong | 5 | accmd_setreboottime 5 " + deviceId + " " + newDeviceSettings.getRebootTime().replace(":", " ") + " \"" + simpleDateFormat.format(new Date()) + "\"");
                }
            }
            return JSON.toJSONString(new Result(true, true));
        } catch (Exception e) {
            LOG.warn(e);
            return JSON.toJSONString(new Result(false, e.getMessage()));
        }
    }

//    public static void main(String[] args) {
//        try (Jedis jedis = JedisPool.getResource()) {
//            jedis.select(Config.getTableCommandIndex());
//            System.out.printf(jedis.lpop("40A5EF846C16"));
//        }
//    }

    @Override
    public String getSettings(String deviceId) {
        try {
            return JSON.toJSONString(new Result(true, deviceDao.selectDeviceSettings(deviceId)));
        } catch (Exception e) {
            return JSON.toJSONString(new Result(false, e.getMessage()));
        }
    }

    @Override
    public String sync(String deviceId) {
        try {
            commandDao.rPush(deviceId, "Pong | 6 | accmd_getsettings 6 " + deviceId);
            return JSON.toJSONString(new Result(true, true));
        } catch (Exception e) {
            LOG.warn(e);
            return JSON.toJSONString(new Result(false, e.getMessage()));
        }
    }

    @Override
    public String reboot(String deviceId) {
        try {
            if (commandDao.rPush(deviceId, "Pong | 0 | reboot") != 0) {
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
