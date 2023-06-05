package com.bs.store.service.impl;

import com.bs.store.dao.UserMapper;
import com.bs.store.entity.TCart;
import com.bs.store.dao.TCartDao;
import com.bs.store.service.TCartService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (TCart)表服务实现类
 *
 * @author makejava
 * @since 2022-11-07 17:18:49
 */
@Service("tCartService")
public class TCartServiceImpl implements TCartService {
    @Resource
    private TCartDao tCartDao;
    @Resource
    private UserMapper userMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param cid 主键
     * @return 实例对象
     */
    @Override
    public TCart queryById(Integer cid) {
        return this.tCartDao.queryById(cid);
    }

    @Override
    public List<TCart> queryByUid(Integer uid) {
        return tCartDao.queryByUid(uid);
    }

    @Override
    public TCart queryByGid(Integer pid) {
        return tCartDao.queryByGid(pid);
    }

    @Override
    public List<TCart> queryAllByLimit(Integer pageNum, Integer pageSize) {
        return tCartDao.queryAllByLimit(pageNum,pageSize);
    }

    @Override
    public long count() {
        return tCartDao.count();
    }

    @Override
    public List<TCart> searchByKey(String searchKey, Integer pageNum, Integer pageSize) {
        return tCartDao.searchByKey(searchKey,pageNum,pageSize);
    }

    @Override
    public long searchCount(String searchKey) {
        return tCartDao.searchCount(searchKey);
    }


    /**
     * 新增数据
     *
     * @param tCart 实例对象
     * @return 实例对象
     */

    @Override
    public int insert(TCart tCart) {
        System.out.println(tCart);
        TCart tCart1 = tCartDao.queryByGid(tCart.getPid());
        if(tCart1!=null){
            return -1;  //表示该商品已加入购物车
        }else{
            tCart.setCreatedUser(userMapper.selectUserById(tCart.getUid()).getUsername());
            tCart.setModifiedUser(userMapper.selectUserById(tCart.getUid()).getUsername());
            Date date = new Date();//java.util.Date
            tCart.setCreatedTime(date);
            tCart.setModifiedTime(date);
            return tCartDao.insert(tCart);
        }

    }

    /**
     * 修改数据
     *
     * @param tCart 实例对象
     * @return 实例对象
     */
    @Override
    public TCart update(TCart tCart) {
        this.tCartDao.update(tCart);
        return this.queryById(tCart.getCid());
    }

    @Override
    public int updateByPidAndUid(TCart tCart) {
        return tCartDao.updateByPidAndUid(tCart);
    }

    /**
     * 通过主键删除数据
     *
     * @param cid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer cid) {
        return this.tCartDao.deleteById(cid) > 0;
    }
}
