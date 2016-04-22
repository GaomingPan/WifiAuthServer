package cn.com.octodata.auth.dao.impl;

import cn.com.octodata.auth.dao.AreaDao;
import cn.com.octodata.auth.model.Area;
import cn.com.octodata.auth.model.BlackAndWhiteList;
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
public class AreaDaoImpl extends BaseDaoImpl<Area, String> implements AreaDao {
    /**
     * 插入一条数据
     *
     * @param area 欲插入的新数据
     * @return 改变的行数
     * @throws Exception
     */
    @Override
    public int insert(Area area) throws Exception {
        try (Connection connection = JDBCPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO area (areaId, areaInfoJsonString, blackList, whiteList) VALUE (?, ?, ?, ?)");
            preparedStatement.setString(1, area.getAreaID());
            preparedStatement.setString(2, area.getAreaInfoJsonString());
            preparedStatement.setString(3, area.getBlackList());
            preparedStatement.setString(4, area.getWhiteList());
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM area WHERE areaId = ?");
            preparedStatement.setString(1, s);
            return preparedStatement.executeUpdate();
        }
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
        try (Connection connection = JDBCPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE area SET areaInfoJsonString = ?, blackList = ?, whiteList = ? WHERE areaId = ?");
            preparedStatement.setString(1, area.getAreaInfoJsonString());
            preparedStatement.setString(2, area.getBlackList());
            preparedStatement.setString(3, area.getWhiteList());
            preparedStatement.setString(4, area.getAreaID());
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
    public Area select(String s) throws Exception {
        try (Connection connection = JDBCPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT areaId,areaInfoJsonString,blackList,whiteList FROM area WHERE areaId = ?");
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            Area area = null;
            if (resultSet.next()) {
                area = new Area(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            }
            return area;
        }
    }

    /**
     * 查询全表数据
     *
     * @return 全表数据的列表
     * @throws Exception
     */
    @Override
    public List<Area> selectAll() throws Exception {
        try (Connection connection = JDBCPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT areaId,areaInfoJsonString,blackList,whiteList FROM area");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Area> areaList = new ArrayList<>();
            while (resultSet.next()) {
                areaList.add(new Area(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
            }
            return areaList;
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM area");
            preparedStatement.executeQuery();
            return preparedStatement.executeQuery().getInt(1);
        }
    }

    @Override
    public int updateAreaInfoJsonString(String areaId, String areaInfoJsonString) throws Exception {
        try (Connection connection = JDBCPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE area SET areaInfoJsonString = ? WHERE areaId = ?");
            preparedStatement.setString(1, areaInfoJsonString);
            preparedStatement.setString(2, areaId);
            return preparedStatement.executeUpdate();
        }
    }

    @Override
    public int updateBlackAndWhiteList(String areaId, BlackAndWhiteList blackAndWhiteList) throws Exception {
        try (Connection connection = JDBCPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE area SET blackList = ? ,whiteList =? WHERE areaId = ?");
            preparedStatement.setString(1, blackAndWhiteList.getBlackList());
            preparedStatement.setString(2, blackAndWhiteList.getWhiteList());
            preparedStatement.setString(3, areaId);
            return preparedStatement.executeUpdate();
        }
    }


    @Override
    public String selectAreaInfoJsonString(String areaId) throws Exception {
        try (Connection connection = JDBCPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT areaInfoJsonString FROM area WHERE areaId = ?");
            preparedStatement.setString(1, areaId);
            ResultSet resultSet = preparedStatement.executeQuery();
            String areaInfoJsonString = null;
            if (resultSet.next()) {
                areaInfoJsonString = resultSet.getString(1);
            }
            return areaInfoJsonString;
        }
    }

    @Override
    public BlackAndWhiteList selectBlackAndWhiteList(String areaId) throws Exception {
        try (Connection connection = JDBCPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT blackList,whiteList FROM area WHERE areaId = ?");
            preparedStatement.setString(1, areaId);
            ResultSet resultSet = preparedStatement.executeQuery();
            BlackAndWhiteList blackAndWhiteList = null;
            if (resultSet.next()) {
                blackAndWhiteList = new BlackAndWhiteList(resultSet.getString(1), resultSet.getString(2));
            }
            return blackAndWhiteList;
        }
    }

}
