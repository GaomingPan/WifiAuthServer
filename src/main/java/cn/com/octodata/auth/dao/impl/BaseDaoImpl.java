package cn.com.octodata.auth.dao.impl;

import cn.com.octodata.auth.dao.BaseDao;

import java.util.List;

/**
 * Created by aran on 16-2-24.
 */
public class BaseDaoImpl<Model, PK> implements BaseDao<Model, PK> {
    /**
     * 插入一条数据
     *
     * @param model 欲插入的新数据
     * @return 改变的行数
     * @throws Exception
     */
    @Override
    public int insert(Model model) throws Exception {
        return 0;
    }

    /**
     * 删除一条数据
     *
     * @param pk 欲删除数据的主键
     * @return 改变的行数
     * @throws Exception
     */
    @Override
    public int delete(PK pk) throws Exception {
        return 0;
    }

    /**
     * 更新一条数据
     *
     * @param model 欲更新的数据
     * @return 更改的行数
     * @throws Exception
     */
    @Override
    public int update(Model model) throws Exception {
        return 0;
    }

    /**
     * 查询一条数据
     *
     * @param pk 欲查询数据的主键
     * @return 查询得到的结果
     * @throws Exception
     */
    @Override
    public Model select(PK pk) throws Exception {
        return null;
    }

    /**
     * 查询全表数据
     *
     * @return 全表数据的列表
     * @throws Exception
     */
    @Override
    public List<Model> selectAll() throws Exception {
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
        return 0;
    }
}
