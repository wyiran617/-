package com.bs.store.service.impl;

import com.bs.store.entity.TDictDistrict;
import com.bs.store.dao.TDictDistrictDao;
import com.bs.store.service.TDictDistrictService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (TDictDistrict)表服务实现类
 *
 * @author makejava
 * @since 2022-11-07 17:19:03
 */
@Service("tDictDistrictService")
public class TDictDistrictServiceImpl implements TDictDistrictService {
    @Resource
    private TDictDistrictDao tDictDistrictDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TDictDistrict queryById(Integer id) {
        return this.tDictDistrictDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param tDictDistrict 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    @Override
    public Page<TDictDistrict> queryByPage(TDictDistrict tDictDistrict, PageRequest pageRequest) {
        long total = this.tDictDistrictDao.count(tDictDistrict);
        return new PageImpl<>(this.tDictDistrictDao.queryAllByLimit(tDictDistrict, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param tDictDistrict 实例对象
     * @return 实例对象
     */
    @Override
    public TDictDistrict insert(TDictDistrict tDictDistrict) {
        this.tDictDistrictDao.insert(tDictDistrict);
        return tDictDistrict;
    }

    /**
     * 修改数据
     *
     * @param tDictDistrict 实例对象
     * @return 实例对象
     */
    @Override
    public TDictDistrict update(TDictDistrict tDictDistrict) {
        this.tDictDistrictDao.update(tDictDistrict);
        return this.queryById(tDictDistrict.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tDictDistrictDao.deleteById(id) > 0;
    }
}
