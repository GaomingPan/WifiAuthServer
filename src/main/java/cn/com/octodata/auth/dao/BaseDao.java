package cn.com.octodata.auth.dao;

import java.util.List;

/**
 * 所有自定义Dao的顶级接口,封装常用的增删改查计操作.
 * Model:代表数据库中的表映射的Java对象类型
 * PK:代表对象的主键类型
 * Created by aran on 16-2-19.
 */
public interface BaseDao<Model, PK> {
    /**
     * 插入一条数据
     *
     * @param model 欲插入的新数据
     * @return 改变的行数
     * @throws Exception
     */
    int insert(Model model) throws Exception;

    /**
     * 删除一条数据
     *
     * @param pk 欲删除数据的主键
     * @return 改变的行数
     * @throws Exception
     */
    int delete(PK pk) throws Exception;

    /**
     * 更新一条数据
     *
     * @param model 欲更新的数据
     * @return 更改的行数
     * @throws Exception
     */
    int update(Model model) throws Exception;

    /**
     * 查询一条数据
     *
     * @param pk 欲查询数据的主键
     * @return 查询得到的结果
     * @throws Exception
     */
    Model select(PK pk) throws Exception;

    /**
     * 查询全表数据
     *
     * @return 全表数据的列表
     * @throws Exception
     */
    List<Model> selectAll() throws Exception;

    /**
     * 数据计数
     *
     * @return 所有数据的计数
     * @throws Exception
     */
    int countAll() throws Exception;
}
