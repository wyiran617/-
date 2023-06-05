package com.bs.store.controller;

import com.bs.store.entity.TAddress;
import com.bs.store.service.TAddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (TAddress)表控制层
 *
 * @author makejava
 * @since 2022-11-07 16:57:38
 */
@RestController
@RequestMapping("tAddress")
public class TAddressController {
    /**
     * 服务对象
     */
    @Resource
    private TAddressService tAddressService;

    @GetMapping("/page")
    public Map<String,Object> queryByPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        long total = tAddressService.count();
        pageNum = (pageNum-1)*pageSize;
        List<TAddress> addressList = tAddressService.queryAllByLimit(pageNum, pageSize);
        Map<String,Object> res = new HashMap<>();
        res.put("total",total);
        res.put("data",addressList);
        return res;
    }

    /**
     * 模糊分页查询
     * @param searchKey 关键字
     * @param pageNum   当前页码
     * @param pageSize 数据个数
     * @return 查询结果
     */
    @GetMapping("/searchPage")
    public Map<String,Object> searchByPage(@RequestParam String searchKey, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        long total = tAddressService.searchCount(searchKey);
        pageNum = (pageNum-1)*pageSize;
        List<TAddress> addressList = tAddressService.searchByKey(searchKey,pageNum, pageSize);
        Map<String,Object> res = new HashMap<>();
        res.put("total",total);
        res.put("data",addressList);
        return res;
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<TAddress> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.tAddressService.queryById(id));
    }

    @GetMapping("/allByUid/{id}")
    public List<TAddress> selectAllByUid(@PathVariable Integer id){
        return this.tAddressService.queryAllByUid(id);
    }

    /**
     * 新增数据
     *
     * @param tAddress 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<TAddress> add(@RequestBody TAddress tAddress) {
        return ResponseEntity.ok(this.tAddressService.insert(tAddress));
    }

    /**
     * 编辑数据
     *
     * @param tAddress 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<TAddress> edit(@RequestBody  TAddress tAddress) {
        return ResponseEntity.ok(this.tAddressService.update(tAddress));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.tAddressService.deleteById(id));
    }

}

