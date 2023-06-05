package com.bs.store.dao;

import com.bs.store.entity.TGoodsCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TGoodsCategory)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-07 17:19:18
 */
public interface TGoodsCategoryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TGoodsCategory queryById(Integer id);


    List<TGoodsCategory> queryAll();


    List<TGoodsCategory> queryIsParent();

    List<TGoodsCategory> queryNoParent();

    /**
     * 查询指定行数据
     *
     * @param pageNum   当前页数
     * @param pageSize 页面数据个数
     * @return 对象列表
     */
    List<TGoodsCategory> queryAllByLimit(Integer pageNum, Integer pageSize);

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
    List<TGoodsCategory> searchByKey(String searchKey,Integer pageNum,Integer pageSize);

    /**
     *
     * @param searchKey 查询关键字
     * @return
     */
    long searchCount(String searchKey);

    /**
     * 新增数据
     *
     * @param tGoodsCategory 实例对象
     * @return 影响行数
     */
    int insert(TGoodsCategory tGoodsCategory);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TGoodsCategory> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TGoodsCategory> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TGoodsCategory> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TGoodsCategory> entities);

    /**
     * 修改数据
     *
     * @param tGoodsCategory 实例对象
     * @return 影响行数
     */
    int update(TGoodsCategory tGoodsCategory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

