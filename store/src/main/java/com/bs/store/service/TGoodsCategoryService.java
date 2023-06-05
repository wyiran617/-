package com.bs.store.service;


import com.bs.store.entity.TGoods;
import com.bs.store.entity.TGoodsCategory;

import java.util.List;

/**
 * (TGoodsCategory)表服务接口
 *
 * @author makejava
 * @since 2022-11-07 17:19:18
 */
public interface TGoodsCategoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TGoodsCategory queryById(Integer id);

    List<TGoodsCategory> queryAll();

    List<TGoodsCategory> queryNoParent();

    List<TGoodsCategory> queryIsParent();
    /**
     * 分页查询
     *
     * @param pageNum   当前页数
     * @param pageSize 页面数据个数
     * @return 对象列表
     */
    List<TGoodsCategory> queryByPage(Integer pageNum, Integer pageSize);
    /**
     * 统计总行数
     * @return 总行数
     */
    long count();



    /**
     * 模糊分页查询
     * @param searchKey 查询关键字
     * @param pageNum   当前页数
     * @param pageSize 页面数据个数
     */
    List<TGoodsCategory> searchByKey(String searchKey, Integer pageNum, Integer pageSize);

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
     * @return 实例对象
     */
    TGoodsCategory insert(TGoodsCategory tGoodsCategory);

    /**
     * 修改数据
     *
     * @param tGoodsCategory 实例对象
     * @return 实例对象
     */
    TGoodsCategory update(TGoodsCategory tGoodsCategory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
