package com.bs.store.controller;

import com.bs.store.entity.TOrder;
import com.bs.store.service.TOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (TOrder)表控制层
 *
 * @author makejava
 * @since 2023-05-09 13:47:38
 */
@RestController
@RequestMapping("tOrder")
public class TOrderController {
    /**
     * 服务对象
     */
    @Resource
    private TOrderService tOrderService;

    /**
     * 分页查询
     *
     * @param pageNum 当前页码
     * @param pageSize    数据个数
     * @return 查询结果
     */
    @GetMapping("/page")
    public Map<String,Object> queryByPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        long total = tOrderService.count();
        pageNum = (pageNum-1)*pageSize;
        List<TOrder> TOrderList = tOrderService.queryAllByLimit(pageNum, pageSize);
        Map<String,Object> res = new HashMap<>();
        res.put("total",total);
        res.put("data",TOrderList);
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
        long total = tOrderService.searchCount(searchKey);
        pageNum = (pageNum-1)*pageSize;
        List<TOrder> TOrderItems = tOrderService.searchByKey(searchKey,pageNum, pageSize);
        Map<String,Object> res = new HashMap<>();
        res.put("total",total);
        res.put("data",TOrderItems);
        return res;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<TOrder> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.tOrderService.queryById(id));
    }

    @GetMapping("/queryAll")
    public List<TOrder> queryAll() {
        return tOrderService.queryAll();
    }

    /**
     * 新增数据
     *
     * @param tOrder 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<TOrder> add(@RequestBody TOrder tOrder) {
        return ResponseEntity.ok(this.tOrderService.insert(tOrder));
    }

    /**
     * 编辑数据
     *
     * @param tOrder 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<TOrder> edit(TOrder tOrder) {
        return ResponseEntity.ok(this.tOrderService.update(tOrder));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable  String id) {
        return ResponseEntity.ok(this.tOrderService.deleteById(id));
    }

}

