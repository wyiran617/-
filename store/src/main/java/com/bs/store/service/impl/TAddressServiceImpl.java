package com.bs.store.service.impl;

import com.bs.store.dao.UserMapper;
import com.bs.store.entity.TAddress;
import com.bs.store.dao.TAddressDao;
import com.bs.store.service.TAddressService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (TAddress)表服务实现类
 *
 * @author makejava
 * @since 2022-11-07 16:57:39
 */
@Service("tAddressService")
public class TAddressServiceImpl implements TAddressService {
    @Resource
    private TAddressDao tAddressDao;
    @Resource
    private UserMapper userMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param aid 主键
     * @return 实例对象
     */
    @Override
    public TAddress queryById(Integer aid) {
        return this.tAddressDao.queryById(aid);
    }

    @Override
    public List<TAddress> queryAllByUid(Integer uid) {
        return tAddressDao.queryAllByUid(uid);
    }

    @Override
    public List<TAddress> queryAllByLimit(Integer pageNum, Integer pageSize) {
        return tAddressDao.queryAllByLimit(pageNum, pageSize);
    }

    @Override
    public long count() {
        return tAddressDao.count();
    }

    @Override
    public List<TAddress> searchByKey(String searchKey, Integer pageNum, Integer pageSize) {
        return tAddressDao.searchByKey(searchKey, pageNum, pageSize);
    }

    @Override
    public long searchCount(String searchKey) {
        return tAddressDao.searchCount(searchKey);
    }


    /**
     * 新增数据
     *
     * @param tAddress 实例对象
     * @return 实例对象
     */
    @Override
    public TAddress insert(TAddress tAddress) {
        tAddress.setCreatedUser(userMapper.selectUserById(tAddress.getUid()).getUsername());
        tAddress.setModifiedUser(userMapper.selectUserById(tAddress.getUid()).getUsername());
        Date date = new Date();//java.util.Date
        tAddress.setCreatedTime(date);
        tAddress.setModifiedTime(date);
        this.tAddressDao.insert(tAddress);
        return tAddress;
    }

    /**
     * 修改数据
     *
     * @param tAddress 实例对象
     * @return 实例对象
     */
    @Override
    public TAddress update(TAddress tAddress) {
        this.tAddressDao.update(tAddress);
        return this.queryById(tAddress.getAid());
    }

    /**
     * 通过主键删除数据
     *
     * @param aid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer aid) {
        return this.tAddressDao.deleteById(aid) > 0;
    }
}
