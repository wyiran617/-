package com.bs.store.service.impl;

import com.bs.store.dao.TGoodsCategoryDao;
import com.bs.store.dao.UserMapper;
import com.bs.store.entity.TGoods;
import com.bs.store.dao.TGoodsDao;
import com.bs.store.entity.TGoodsCategory;
import com.bs.store.service.TGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * (TGoods)表服务实现类
 *
 * @author makejava
 * @since 2022-11-07 17:04:08
 */
@Service("tGoodsService")
public class TGoodsServiceImpl implements TGoodsService {
    @Resource
    private TGoodsDao tGoodsDao;
    @Resource
    private TGoodsCategoryDao categoryDao;

    @Resource
    private UserMapper userMapper;

    @Override
    public List<TGoods> queryAll() {
        return tGoodsDao.queryAll();
    }

    //获取类型为电子产品的goods
    @Override
    public List<TGoods> getElectronicsList() {
        List<TGoods> tGoodsList = tGoodsDao.queryAll();
        List<TGoods> getElectronicsList = new ArrayList<>();
        for (int i = 0; i < tGoodsList.size(); i++) {
            Long parentId = categoryDao.queryById(tGoodsList.get(i).getCategoryId()).getParentId();
            if(parentId==1){
                getElectronicsList.add(tGoodsList.get(i));
            }
        }
        if (getElectronicsList.size()>7){
            return getElectronicsList.subList(0,7);
        }
        return getElectronicsList;
    }


    //获取类型为办公文具的goods
    @Override
    public List<TGoods> getApplianceList() {
        List<TGoods> tGoodsList = tGoodsDao.queryAll();
        List<TGoods> getApplianceList = new ArrayList<>();
        for (int i = 0; i < tGoodsList.size(); i++) {
            Long parentId = categoryDao.queryById(tGoodsList.get(i).getCategoryId()).getParentId();
            if(parentId==2){
                getApplianceList.add(tGoodsList.get(i));
            }
        }
        if(getApplianceList.size()>7){
            return getApplianceList.subList(0,7);
        }
        return getApplianceList;
    }

    //获取类型为生活用品的goods
    @Override
    public List<TGoods> getShowLifeList() {
        List<TGoods> tGoodsList = tGoodsDao.queryAll();
        List<TGoods> getShowLifeList = new ArrayList<>();
        for (int i = 0; i < tGoodsList.size(); i++) {
            Long parentId = categoryDao.queryById(tGoodsList.get(i).getCategoryId()).getParentId();
            if(parentId==3){
                getShowLifeList.add(tGoodsList.get(i));
            }
        }
        if (getShowLifeList.size() > 7) {
            return getShowLifeList.subList(0,7);
        }
        return getShowLifeList;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TGoods queryById(Integer id) {
        return this.tGoodsDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param pageNum      分页页码
     * @param pageSize 数据个数
     * @return 查询结果
     */
    @Override
    public List<TGoods> queryByPage(Integer pageNum, Integer pageSize) {
        List<TGoods> tGoods = tGoodsDao.queryAllByLimit(pageNum, pageSize);
        return tGoods;
    }

    @Override
    public long count() {
        long total = tGoodsDao.count();
        return total;
    }

    @Override
    public List<TGoods> byTypeIdPage(int categoryId, Integer pageNum, Integer pageSize) {
        return tGoodsDao.byTypeIdPage(categoryId,pageNum,pageSize);
    }

    @Override
    public long byTypeIdCount(int categoryId) {
        return tGoodsDao.byTypeIdCount(categoryId);
    }

    @Override
    public List<TGoods> searchByKey(String searchKey, Integer pageNum, Integer pageSize) {
        return tGoodsDao.searchByKey(searchKey, pageNum, pageSize);
    }

    @Override
    public long searchCount(String searchKey) {
        return tGoodsDao.searchCount(searchKey);
    }

    /**
     * 新增数据
     *
     * @param tGoods 实例对象
     * @return 实例对象
     */
    @Override
    public TGoods insert(int uid,TGoods tGoods) {
        tGoods.setCreatedUser(userMapper.selectUserById(uid).getUsername());
        tGoods.setModifiedUser(userMapper.selectUserById(uid).getUsername());
        Date date = new Date();
        tGoods.setCreatedTime(date);
        tGoods.setModifiedTime(date);
        this.tGoodsDao.insert(tGoods);
        return tGoods;
    }

    /**
     * 修改数据
     *
     * @param tGoods 实例对象
     * @return 实例对象
     */
    @Override
    public TGoods update(TGoods tGoods) {
        this.tGoodsDao.update(tGoods);
        return this.queryById(tGoods.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tGoodsDao.deleteById(id) > 0;
    }
}
