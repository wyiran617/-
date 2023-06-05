package com.bs.store.service;

import com.bs.store.entity.TOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (TOrder)表服务接口
 *
 * @author makejava
 * @since 2023-05-09 13:47:38
 */
public interface TOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param oid 主键
     * @return 实例对象
     */
    TOrder queryById(String oid);

    List<TOrder> queryAll();


    List<TOrder> queryAllByLimit(Integer pageNum, Integer pageSize);
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
    List<TOrder> searchByKey(String searchKey, Integer pageNum, Integer pageSize);

    /**
     *
     * @param searchKey 查询关键字
     * @return
     */
    long searchCount(String searchKey);

    /**
     * 新增数据
     *
     * @param tOrder 实例对象
     * @return 实例对象
     */
    TOrder insert(TOrder tOrder);

    /**
     * 修改数据
     *
     * @param tOrder 实例对象
     * @return 实例对象
     */
    TOrder update(TOrder tOrder);

    /**
     * 通过主键删除数据
     *
     * @param oid 主键
     * @return 是否成功
     */
    boolean deleteById(String oid);

}
