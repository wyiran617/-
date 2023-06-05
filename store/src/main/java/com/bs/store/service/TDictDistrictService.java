package com.bs.store.service;

import com.bs.store.entity.TDictDistrict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (TDictDistrict)表服务接口
 *
 * @author makejava
 * @since 2022-11-07 17:19:03
 */
public interface TDictDistrictService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TDictDistrict queryById(Integer id);

    /**
     * 分页查询
     *
     * @param tDictDistrict 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    Page<TDictDistrict> queryByPage(TDictDistrict tDictDistrict, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param tDictDistrict 实例对象
     * @return 实例对象
     */
    TDictDistrict insert(TDictDistrict tDictDistrict);

    /**
     * 修改数据
     *
     * @param tDictDistrict 实例对象
     * @return 实例对象
     */
    TDictDistrict update(TDictDistrict tDictDistrict);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
