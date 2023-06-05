package com.bs.store.service;

import com.bs.store.entity.TAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (TAddress)表服务接口
 *
 * @author makejava
 * @since 2022-11-07 16:57:39
 */
public interface TAddressService {

    /**
     * 通过ID查询单条数据
     *
     * @param aid 主键
     * @return 实例对象
     */
    TAddress queryById(Integer aid);

    List<TAddress> queryAllByUid(Integer uid);

    List<TAddress> queryAllByLimit(Integer pageNum, Integer pageSize);

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
    List<TAddress> searchByKey(String searchKey, Integer pageNum, Integer pageSize);

    /**
     *
     * @param searchKey 查询关键字
     * @return
     */
    long searchCount(String searchKey);

    /**
     * 新增数据
     *
     * @param tAddress 实例对象
     * @return 实例对象
     */
    TAddress insert(TAddress tAddress);

    /**
     * 修改数据
     *
     * @param tAddress 实例对象
     * @return 实例对象
     */
    TAddress update(TAddress tAddress);

    /**
     * 通过主键删除数据
     *
     * @param aid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer aid);

}
