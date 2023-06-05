package com.bs.store.service.impl;

import com.bs.store.entity.TGoodsCategory;
import com.bs.store.dao.TGoodsCategoryDao;
import com.bs.store.service.TGoodsCategoryService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * (TGoodsCategory)表服务实现类
 *
 * @author makejava
 * @since 2022-11-07 17:19:18
 */
@Service("tGoodsCategoryService")
public class TGoodsCategoryServiceImpl implements TGoodsCategoryService {
    @Resource
    private TGoodsCategoryDao tGoodsCategoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TGoodsCategory queryById(Integer id) {
        return this.tGoodsCategoryDao.queryById(id);
    }

    @Override
    public List<TGoodsCategory> queryAll() {
        return this.tGoodsCategoryDao.queryAll();
    }

    @Override
    public List<TGoodsCategory> queryNoParent() {
        return tGoodsCategoryDao.queryNoParent();
    }

    @Override
    public List<TGoodsCategory> queryIsParent() {
        return tGoodsCategoryDao.queryIsParent();
    }

    @Override
    public List<TGoodsCategory> queryByPage(Integer pageNum, Integer pageSize) {
        return tGoodsCategoryDao.queryAllByLimit(pageNum,pageSize);
    }

    @Override
    public long count() {
        return tGoodsCategoryDao.count();
    }

    @Override
    public List<TGoodsCategory> searchByKey(String searchKey, Integer pageNum, Integer pageSize) {
        return tGoodsCategoryDao.searchByKey(searchKey, pageNum, pageSize);
    }

    @Override
    public long searchCount(String searchKey) {
        return tGoodsCategoryDao.searchCount(searchKey) ;
    }


    /**
     * 新增数据
     *
     * @param tGoodsCategory 实例对象
     * @return 实例对象
     */
    @Override
    public TGoodsCategory insert(TGoodsCategory tGoodsCategory) {
        this.tGoodsCategoryDao.insert(tGoodsCategory);
        return tGoodsCategory;
    }

    /**
     * 修改数据
     *
     * @param tGoodsCategory 实例对象
     * @return 实例对象
     */
    @Override
    public TGoodsCategory update(TGoodsCategory tGoodsCategory) {
        this.tGoodsCategoryDao.update(tGoodsCategory);
        return this.queryById(tGoodsCategory.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tGoodsCategoryDao.deleteById(id) > 0;
    }
}
