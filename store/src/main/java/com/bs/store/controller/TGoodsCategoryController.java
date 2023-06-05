package com.bs.store.controller;

import com.bs.store.entity.TGoodsCategory;
import com.bs.store.service.TGoodsCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (TGoodsCategory)表控制层
 *
 * @author makejava
 * @since 2022-11-07 17:19:18
 */
@RestController
@RequestMapping("tGoodsCategory")
public class TGoodsCategoryController {
    /**
     * 服务对象
     */
    @Resource
    private TGoodsCategoryService tGoodsCategoryService;

    @GetMapping("/all")
    public List<TGoodsCategory> queryAll(){
        return tGoodsCategoryService.queryAll();
    }

    @GetMapping("/noParent")
    public List<TGoodsCategory> queryNoParent(){
        return tGoodsCategoryService.queryNoParent();
    }


    @GetMapping("/isParent")
    public List<TGoodsCategory> queryIsParent(){
        return tGoodsCategoryService.queryIsParent();
    }

    /**
     * 分页查询
     *
     * @param pageNum 当前页码
     * @param pageSize    数据个数
     * @return 查询结果
     */
    @GetMapping("/page")
    public Map<String,Object> queryByPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        long total = tGoodsCategoryService.count();
        pageNum = (pageNum-1)*pageSize;
        List<TGoodsCategory> tGoodsType = tGoodsCategoryService.queryByPage(pageNum, pageSize);
        Map<String,Object> res = new HashMap<>();
        res.put("total",total);
        res.put("data",tGoodsType);
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
        long total = tGoodsCategoryService.searchCount(searchKey);
        pageNum = (pageNum-1)*pageSize;
        List<TGoodsCategory> tGoods = tGoodsCategoryService.searchByKey(searchKey,pageNum, pageSize);
        Map<String,Object> res = new HashMap<>();
        res.put("total",total);
        res.put("data",tGoods);
        return res;
    }
    

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<TGoodsCategory> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.tGoodsCategoryService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param tGoodsCategory 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<TGoodsCategory> add(@RequestBody TGoodsCategory tGoodsCategory) {
        return ResponseEntity.ok(this.tGoodsCategoryService.insert(tGoodsCategory));
    }

    /**
     * 编辑数据
     *
     * @param tGoodsCategory 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<TGoodsCategory> edit(@RequestBody TGoodsCategory tGoodsCategory) {
        return ResponseEntity.ok(this.tGoodsCategoryService.update(tGoodsCategory));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.tGoodsCategoryService.deleteById(id));
    }

}

