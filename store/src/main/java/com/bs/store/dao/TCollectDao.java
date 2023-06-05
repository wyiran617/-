package com.bs.store.dao;

import com.bs.store.entity.TCollect;
import com.bs.store.entity.TGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (TCollect)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-07 15:25:23
 */
public interface TCollectDao {

    /**
     * 通过ID查询单条数据
     *
     * @param cid 主键
     * @return 实例对象
     */
    TCollect queryById(Integer cid);

    TCollect queryByGid(Integer id);

    List<TCollect> queryByUid(Integer uid);

    List<TCollect> searchByKey(String searchKey, Integer pageNum, Integer pageSize);

    long searchCount(String searchKey);
    /**
     * 查询指定行数据
     *         分页对象
     * @return 对象列表
     */
    List<TCollect> queryAllByLimit(Integer pageNum,Integer pageSize);

    /**
     * 统计总行数
     * @return 总行数
     */
    long count();

    /**
     * 新增数据
     *
     * @param tCollect 实例对象
     * @return 影响行数
     */
    int insert(TCollect tCollect);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TCollect> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TCollect> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TCollect> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TCollect> entities);

    /**
     * 修改数据
     *
     * @param tCollect 实例对象
     * @return 影响行数
     */
    int update(TCollect tCollect);

    /**
     * 通过主键删除数据
     *
     * @param cid 主键
     * @return 影响行数
     */
    int deleteById(Integer cid);

}

