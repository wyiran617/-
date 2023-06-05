package com.bs.store.dao;

import com.bs.store.entity.TCart;
import com.bs.store.entity.TGoodsCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (TCart)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-07 17:18:49
 */
public interface TCartDao {

    /**
     * 通过ID查询单条数据
     *
     * @param cid 主键
     * @return 实例对象
     */
    TCart queryById(Integer cid);
    List<TCart> queryByUid(Integer uid);

    TCart queryByGid(Integer pid);

    /**
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<TCart> queryAllByLimit(Integer pageNum, Integer pageSize);

    /**
     * 统计总行数
     *
     *
     * @return 总行数
     */
    long count();



    /**
     * 模糊分页查询
     * @param searchKey 查询关键字
     * @param pageNum   当前页数
     * @param pageSize 页面数据个数
     */
    List<TCart> searchByKey(String searchKey, Integer pageNum, Integer pageSize);

    /**
     *
     * @param searchKey 查询关键字
     * @return
     */
    long searchCount(String searchKey);


    /**
     * 新增数据
     *
     * @param tCart 实例对象
     * @return 影响行数
     */
    int insert(TCart tCart);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TCart> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TCart> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TCart> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TCart> entities);

    /**
     * 修改数据
     *
     * @param tCart 实例对象
     * @return 影响行数
     */
    int update(TCart tCart);

    int updateByPidAndUid(TCart tCart);

    /**
     * 通过主键删除数据
     *
     * @param cid 主键
     * @return 影响行数
     */
    int deleteById(Integer cid);

}

