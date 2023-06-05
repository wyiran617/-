package com.bs.store.service;

import com.bs.store.entity.TCart;
import com.bs.store.entity.TOrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (TOrderItem)表服务接口
 *
 * @author makejava
 * @since 2022-11-07 17:19:49
 */
public interface TOrderItemService {

    /**
     * 通过ID查询单条数据
     *
     * @param oid 主键
     * @return 实例对象
     */
    TOrderItem queryById(String oid);

    List<TOrderItem> queryAll();

    List<TOrderItem> queryAllByOid(String oid);
    List<Date> queryCreatedTimeByOid(String oid);
    Map<String,Object> queryAllByUid(int uid);

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
     * @return 实例对象
     */
    TOrderItem insert(TOrderItem tOrderItem);


    /**
     * 批量增加
     * @param List<TCart> list
     * @return
     */
    int addBatch(String oid,List<TCart> list);

    /**
     * 修改数据
     *
     * @param tOrderItem 实例对象
     * @return 实例对象
     */
    TOrderItem update(TOrderItem tOrderItem);

    /**
     * 通过主键删除数据
     *
     * @param oid 主键
     * @return 是否成功
     */
    boolean deleteById(String oid);

}
