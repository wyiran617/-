package com.bs.store.dao;

import com.bs.store.entity.TDictDistrict;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (TDictDistrict)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-07 17:19:03
 */
public interface TDictDistrictDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TDictDistrict queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param tDictDistrict 查询条件
     * @param pageable      分页对象
     * @return 对象列表
     */
    List<TDictDistrict> queryAllByLimit(TDictDistrict tDictDistrict, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param tDictDistrict 查询条件
     * @return 总行数
     */
    long count(TDictDistrict tDictDistrict);

    /**
     * 新增数据
     *
     * @param tDictDistrict 实例对象
     * @return 影响行数
     */
    int insert(TDictDistrict tDictDistrict);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TDictDistrict> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TDictDistrict> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TDictDistrict> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TDictDistrict> entities);

    /**
     * 修改数据
     *
     * @param tDictDistrict 实例对象
     * @return 影响行数
     */
    int update(TDictDistrict tDictDistrict);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

