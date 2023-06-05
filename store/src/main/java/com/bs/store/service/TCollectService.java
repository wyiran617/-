package com.bs.store.service;

import com.bs.store.entity.TCollect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (TCollect)表服务接口
 *
 * @author makejava
 * @since 2023-04-07 15:25:23
 */
public interface TCollectService {

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
     * @return 实例对象
     */
    int insert(TCollect tCollect);

    /**
     * 修改数据
     *
     * @param tCollect 实例对象
     * @return 实例对象
     */
    TCollect update(TCollect tCollect);

    /**
     * 通过主键删除数据
     *
     * @param cid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer cid);

}
