package com.bs.store.dao;

import com.bs.store.entity.TCart;
import com.bs.store.entity.TOrderItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (TOrderItem)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-07 17:19:49
 */
public interface TOrderItemDao {

    /**
     * 通过ID查询单条数据
     *
     * @param oid 主键
     * @return 实例对象
     */
    TOrderItem queryById(String oid);



    List<TOrderItem> queryAll();
    List<Date> queryCreatedTimeByOid(String oid);
    List<TOrderItem> queryAllByOid(String oid);

    List<TOrderItem> queryAllByUid(int uid);


    /**
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<TOrderItem> queryAllByLimit(Integer pageNum, Integer pageSize);

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
    List<TOrderItem> searchByKey(String searchKey, Integer pageNum, Integer pageSize);

    /**
     *
     * @param searchKey 查询关键字
     * @return
     */
    long searchCount(String searchKey);

    /**
     * 新增数据
     *
     * @param tOrderItem 实例对象
     * @return 影响行数
     */
    int insert(TOrderItem tOrderItem);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TOrderItem> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TOrderItem> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TOrderItem> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TOrderItem> entities);

    /**
     * 修改数据
     *
     * @param tOrderItem 实例对象
     * @return 影响行数
     */
    int update(TOrderItem tOrderItem);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String oid);

}

