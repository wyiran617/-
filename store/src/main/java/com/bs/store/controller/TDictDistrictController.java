package com.bs.store.controller;

import com.bs.store.entity.TDictDistrict;
import com.bs.store.service.TDictDistrictService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TDictDistrict)表控制层
 *
 * @author makejava
 * @since 2022-11-07 17:19:03
 */
@RestController
@RequestMapping("tDictDistrict")
public class TDictDistrictController {
    /**
     * 服务对象
     */
    @Resource
    private TDictDistrictService tDictDistrictService;

    /**
     * 分页查询
     *
     * @param tDictDistrict 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<TDictDistrict>> queryByPage(TDictDistrict tDictDistrict, PageRequest pageRequest) {
        return ResponseEntity.ok(this.tDictDistrictService.queryByPage(tDictDistrict, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<TDictDistrict> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.tDictDistrictService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param tDictDistrict 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<TDictDistrict> add(TDictDistrict tDictDistrict) {
        return ResponseEntity.ok(this.tDictDistrictService.insert(tDictDistrict));
    }

    /**
     * 编辑数据
     *
     * @param tDictDistrict 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<TDictDistrict> edit(TDictDistrict tDictDistrict) {
        return ResponseEntity.ok(this.tDictDistrictService.update(tDictDistrict));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.tDictDistrictService.deleteById(id));
    }

}

