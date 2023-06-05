package com.bs.store.service;

import com.bs.store.entity.TCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (TCart)表服务接口
 *
 * @author makejava
 * @since 2022-11-07 17:18:49
 */
public interface TCartService {

    /**
     * 通过ID查询单条数据
     *
     * @param cid 主键
     * @return 实例对象
     */
    TCart queryById(Integer cid);

    List<TCart> queryByUid(Integer uid);

    TCart queryByGid(Integer pid);

    /**
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<TCart> queryAllByLimit(Integer pageNum, Integer pageSize);

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
    List<TCart> searchByKey(String searchKey, Integer pageNum, Integer pageSize);

    /**
     *
     * @param searchKey 查询关键字
     * @return
     */
    long searchCount(String searchKey);



    /**
     * 新增数据
     *
     * @param tCart 实例对象
     * @return 实例对象
     */
    int insert(TCart tCart);

    /**
     * 修改数据
     *
     * @param tCart 实例对象
     * @return 实例对象
     */
    TCart update(TCart tCart);

    int updateByPidAndUid(TCart tCart);

    /**
     * 通过主键删除数据
     *
     * @param cid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer cid);

}
