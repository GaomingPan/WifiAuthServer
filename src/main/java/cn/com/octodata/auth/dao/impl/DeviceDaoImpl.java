package cn.com.octodata.auth.dao.impl;

import cn.com.octodata.auth.dao.DeviceDao;
import cn.com.octodata.auth.model.Device;
import cn.com.octodata.auth.model.DeviceSettings;
import cn.com.octodata.auth.util.JDBCPool;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aran on 16-2-24.
 */
@Repository
public class DeviceDaoImpl implements DeviceDao {
    /**
     * 插入一条数据
     *
     * @param device 欲插入的新数据
     * @return 改变的行数
     * @throws Exception
     */
    @Override
    public int insert(Device device) throws Exception {
        try (Connection connection = JDBCPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO device (deviceID,areaID,disable,ssid,ssidHiden,encryption,`key`,ipAddress,ipMask,`ignore`,`satrt`,`limit`,leaseTime,autoReboot,rebootTime) VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, device.getDeviceId());
            preparedStatement.setString(2, device.getAreaId());
            preparedStatement.setInt(3, device.getDisable());
            preparedStatement.setString(4, device.getSsid());
            preparedStatement.setInt(5, device.getSsidHidden());
            preparedStatement.setString(6, device.getEncryption());
            preparedStatement.setString(7, device.getKey());
            preparedStatement.setString(8, device.getIpAddress());
            preparedStatement.setString(9, device.getIpMask());
            preparedStatement.setInt(10, device.getIgnore());
            preparedStatement.setInt(11, device.getStart());
            preparedStatement.setInt(12, device.getLimit());
            preparedStatement.setString(13, device.getLeaseTime());
            preparedStatement.setInt(14, device.getAutoReboot());
            preparedStatement.setString(15, device.getRebootTime());
            return preparedStatement.executeUpdate();
        }
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
        try (Connection connection = JDBCPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM device WHERE deviceId = ?");
            preparedStatement.setString(1, s);
            return preparedStatement.executeUpdate();
        }
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
        try (Connection connection = JDBCPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE device SET areaID=?,disable=?,ssid=?,ssidHiden=?,encryption=?,`key`=?,ipAddress=?,ipMask=?,`ignore`=?,`satrt`=?,`limit`=?,leaseTime=?,autoReboot=?,rebootTime=? WHERE deviceId = ?");
            preparedStatement.setString(1, device.getAreaId());
            preparedStatement.setInt(2, device.getDisable());
            preparedStatement.setString(3, device.getSsid());
            preparedStatement.setInt(4, device.getSsidHidden());
            preparedStatement.setString(5, device.getEncryption());
            preparedStatement.setString(6, device.getKey());
            preparedStatement.setString(7, device.getIpAddress());
            preparedStatement.setString(8, device.getIpMask());
            preparedStatement.setInt(9, device.getIgnore());
            preparedStatement.setInt(10, device.getStart());
            preparedStatement.setInt(11, device.getLimit());
            preparedStatement.setString(12, device.getLeaseTime());
            preparedStatement.setInt(13, device.getAutoReboot());
            preparedStatement.setString(14, device.getRebootTime());
            preparedStatement.setString(15, device.getDeviceId());
            return preparedStatement.executeUpdate();
        }
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
        try (Connection connection = JDBCPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT deviceID,areaID,disable,ssid,ssidHiden,encryption,`key`,ipAddress,ipMask,`ignore`,`satrt`,`limit`,leaseTime,autoReboot,rebootTime FROM device WHERE deviceId = ?");
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            Device device = null;
            if (resultSet.next()) {
                device = new Device(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12), resultSet.getString(13), resultSet.getInt(14), resultSet.getString(15));
            }
            return device;
        }
    }

    /**
     * 查询全表数据
     *
     * @return 全表数据的列表
     * @throws Exception
     */
    @Override
    public List<Device> selectAll() throws Exception {
        try (Connection connection = JDBCPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT deviceID,areaID,disable,ssid,ssidHiden,encryption,`key`,ipAddress,ipMask,`ignore`,`satrt`,`limit`,leaseTime,autoReboot,rebootTime FROM device");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Device> deviceList = new ArrayList<>();
            while (resultSet.next()) {
                deviceList.add(new Device(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12), resultSet.getString(13), resultSet.getInt(14), resultSet.getString(15)));
            }
            return deviceList;
        }
    }

    /**
     * 数据计数
     *
     * @return 所有数据的计数
     * @throws Exception
     */
    @Override
    public int countAll() throws Exception {
        try (Connection connection = JDBCPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM device");
            preparedStatement.executeQuery();
            return preparedStatement.executeQuery().getInt(1);
        }
    }

    @Override
    public int updateAreaId(String deviceId, String areaId) throws Exception {
        try (Connection connection = JDBCPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE device SET areaId=? WHERE deviceId = ?");
            preparedStatement.setString(1, areaId);
            preparedStatement.setString(2, deviceId);
            return preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<String> selectDeviceList(String areaId) throws Exception {
        try (Connection connection = JDBCPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT deviceId FROM device WHERE areaId = ?");
            preparedStatement.setString(1, areaId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> deviceList = new ArrayList<>();
            while (resultSet.next()) {
                deviceList.add(resultSet.getString(1));
            }
            return deviceList;
        }
    }

    @Override
    public DeviceSettings selectDeviceSettings(String deviceId) throws Exception {
        try (Connection connection = JDBCPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT disable,ssid,ssidHiden,encryption,`key`,ipAddress,ipMask,`ignore`,`satrt`,`limit`,leaseTime,autoReboot,rebootTime FROM device WHERE deviceId = ?");
            preparedStatement.setString(1, deviceId);
            ResultSet resultSet = preparedStatement.executeQuery();
            DeviceSettings deviceSettings = null;
            if (resultSet.next()) {
                deviceSettings = new DeviceSettings(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getString(11), resultSet.getInt(12), resultSet.getString(13));
            }
            return deviceSettings;
        }
    }
}
