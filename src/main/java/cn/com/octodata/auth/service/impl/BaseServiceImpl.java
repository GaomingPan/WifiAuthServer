package cn.com.octodata.auth.service.impl;

import cn.com.octodata.auth.dao.BaseDao;
import cn.com.octodata.auth.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by aran on 16-2-24.
 */
public class BaseServiceImpl<Model, PK> implements BaseService<Model, PK> {

    @Autowired
    private BaseDao<Model, PK> baseDao;

    /**
     * 插入一条数据
     *
     * @param model 欲插入的数据
     * @return 改变的行数
     * @throws Exception
     */
    @Override
    public int insert(Model model) throws Exception {
        return baseDao.insert(model);
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
        return baseDao.delete(pk);
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
        return baseDao.update(model);
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
        return baseDao.select(pk);
    }

    /**
     * 查询全表数据
     *
     * @return 全表数据的列表
     * @throws Exception
     */
    @Override
    public List<Model> selectAll() throws Exception {
        return baseDao.selectAll();
    }

    /**
     * 数据计数
     *
     * @return 所有数据的计数
     * @throws Exception
     */
    @Override
    public int countAll() throws Exception {
        return baseDao.countAll();
    }
}
