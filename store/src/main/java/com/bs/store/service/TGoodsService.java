package com.bs.store.service;

import com.bs.store.entity.TGoods;

import java.util.List;
import java.util.Map;

/**
 * (TGoods)表服务接口
 *
 * @author makejava
 * @since 2022-11-07 17:04:08
 */
public interface TGoodsService {

    /**
     * 查询所有数据
     * @return
     */
    List<TGoods> queryAll();

    List<TGoods> getElectronicsList();

    List<TGoods> getApplianceList();

    List<TGoods> getShowLifeList();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TGoods queryById(Integer id);

    /**
     * 分页查询
     *
     * @param pageNum   当前页数
     * @param pageSize 页面数据个数
     * @return 对象列表
     */
    List<TGoods> queryByPage(Integer pageNum, Integer pageSize);
    /**
     * 统计总行数
     * @return 总行数
     */
    long count();


    /**
     * 通过分类id分页查询商品数据
     * @param categoryId
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<TGoods> byTypeIdPage(int categoryId, Integer pageNum,Integer pageSize);
    /**
     * 根据分类id统计商品数量
     * @param categoryId
     * @return
     */
    long byTypeIdCount(int categoryId);


    /**
     * 模糊分页查询
     * @param searchKey 查询关键字
     * @param pageNum   当前页数
     * @param pageSize 页面数据个数
     */
    List<TGoods> searchByKey(String searchKey,Integer pageNum,Integer pageSize);

    /**
     *
     * @param searchKey 查询关键字
     * @return
     */
    long searchCount(String searchKey);

    /**
     * 新增数据
     *
     * @param tGoods 实例对象
     * @return 实例对象
     */
    TGoods insert(int uid,TGoods tGoods);

    /**
     * 修改数据
     *
     * @param tGoods 实例对象
     * @return 实例对象
     */
    TGoods update(TGoods tGoods);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
