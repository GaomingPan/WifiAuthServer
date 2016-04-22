package cn.com.octodata.auth.dao.impl;

import cn.com.octodata.auth.dao.LogDao;
import cn.com.octodata.auth.model.Log;
import cn.com.octodata.auth.util.JDBCPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created by aran on 16-3-9.
 */
public class LogDaoImpl implements LogDao {
    /**
     * 插入一条数据
     *
     * @param log 欲插入的新数据
     * @return 改变的行数
     * @throws Exception
     */
    @Override
    public int insert(Log log) throws Exception {
        try (Connection connection = JDBCPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO log (deviceID, userMAC, message) VALUE (?, ?, ?)");
            preparedStatement.setString(1, log.getDeviceID());
            preparedStatement.setString(2, log.getUserMAC());
            preparedStatement.setString(3, log.getMessage());
            return preparedStatement.executeUpdate();
        }
    }

    /**
     * 删除一条数据
     *
     * @param integer 欲删除数据的主键
     * @return 改变的行数
     * @throws Exception
     */
    @Override
    public int delete(Integer integer) throws Exception {
        return 0;
    }

    /**
     * 更新一条数据
     *
     * @param log 欲更新的数据
     * @return 更改的行数
     * @throws Exception
     */
    @Override
    public int update(Log log) throws Exception {
        return 0;
    }

    /**
     * 查询一条数据
     *
     * @param integer 欲查询数据的主键
     * @return 查询得到的结果
     * @throws Exception
     */
    @Override
    public Log select(Integer integer) throws Exception {
        return null;
    }

    /**
     * 查询全表数据
     *
     * @return 全表数据的列表
     * @throws Exception
     */
    @Override
    public List<Log> selectAll() throws Exception {
        return null;
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM log");
            preparedStatement.executeQuery();
            return preparedStatement.executeQuery().getInt(1);
        }
    }
}
