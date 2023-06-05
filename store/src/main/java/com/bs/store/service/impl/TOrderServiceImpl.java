package com.bs.store.service.impl;

import com.bs.store.dao.UserMapper;
import com.bs.store.entity.TOrder;
import com.bs.store.dao.TOrderDao;
import com.bs.store.service.TOrderService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (TOrder)表服务实现类
 *
 * @author makejava
 * @since 2023-05-09 13:47:38
 */
@Service("tOrderService")
public class TOrderServiceImpl implements TOrderService {
    @Resource
    private TOrderDao tOrderDao;
    @Resource
    private UserMapper userMapper;
    /**
     * 通过ID查询单条数据
     *
     * @param oid 主键
     * @return 实例对象
     */
    @Override
    public TOrder queryById(String oid) {
        return this.tOrderDao.queryById(oid);
    }

    @Override
    public List<TOrder> queryAll() {
        return tOrderDao.queryAll();
    }

    @Override
    public List<TOrder> queryAllByLimit(Integer pageNum, Integer pageSize) {
        return tOrderDao.queryAllByLimit(pageNum, pageSize);
    }

    @Override
    public long count() {
        return tOrderDao.count();
    }

    @Override
    public List<TOrder> searchByKey(String searchKey, Integer pageNum, Integer pageSize) {
        return tOrderDao.searchByKey(searchKey, pageNum, pageSize);
    }

    @Override
    public long searchCount(String searchKey) {
        return tOrderDao.searchCount(searchKey);
    }


    /**
     * 新增数据
     *
     * @param tOrder 实例对象
     * @return 实例对象
     */
    @Override
    public TOrder insert(TOrder tOrder) {
        Date date = new Date();
        System.out.println("oid:"+tOrder.getOid());
        tOrder.setStatus(1);
        tOrder.setPayTime(date);
        tOrder.setOrderTime(date);
        tOrder.setCreatedTime(date);
        tOrder.setModifiedTime(date);
        tOrder.setCreatedUser(userMapper.selectUserById(tOrder.getUid()).getUsername());
        tOrder.setModifiedUser(userMapper.selectUserById(tOrder.getUid()).getUsername());
        this.tOrderDao.insert(tOrder);
        return tOrder;
    }

    /**
     * 修改数据
     *
     * @param tOrder 实例对象
     * @return 实例对象
     */
    @Override
    public TOrder update(TOrder tOrder) {
        this.tOrderDao.update(tOrder);
        return this.queryById(tOrder.getOid());
    }

    /**
     * 通过主键删除数据
     *
     * @param oid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String oid) {
        return this.tOrderDao.deleteById(oid) > 0;
    }
}
