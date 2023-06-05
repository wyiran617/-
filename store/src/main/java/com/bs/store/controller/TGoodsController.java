package com.bs.store.controller;

import com.bs.store.entity.TGoods;
import com.bs.store.service.TGoodsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (TGoods)表控制层
 *
 * @author makejava
 * @since 2022-11-07 17:04:07
 */
@RestController
@CrossOrigin
@RequestMapping("tGoods")
public class TGoodsController {
    /**
     * 服务对象
     */
    @Resource
    private TGoodsService tGoodsService;

    /**
     * 分页查询
     *
     * @param pageNum      当前页码
     * @param pageSize 数据个数
     * @return 查询结果
     */
    @GetMapping("/page")
    public Map<String,Object> queryByPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        long total = tGoodsService.count();
        pageNum = (pageNum-1)*pageSize;
        List<TGoods> tGoods = tGoodsService.queryByPage(pageNum, pageSize);
        Map<String,Object> res = new HashMap<>();
        res.put("total",total);
        res.put("data",tGoods);
        return res;
    }

    @GetMapping("/byType")
    public Map<String,Object >byTypePage(@RequestParam int categoryId, @RequestParam Integer pageNum, @RequestParam Integer pageSize){
        long total = tGoodsService.byTypeIdCount(categoryId);
        pageNum = (pageNum-1)*pageSize;
        List<TGoods> tGoods = tGoodsService.byTypeIdPage(categoryId,pageNum, pageSize);
        Map<String,Object> res = new HashMap<>();
        res.put("total",total);
        res.put("data",tGoods);
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
        long total = tGoodsService.searchCount(searchKey);
        pageNum = (pageNum-1)*pageSize;
        List<TGoods> tGoods = tGoodsService.searchByKey(searchKey,pageNum, pageSize);
        Map<String,Object> res = new HashMap<>();
        res.put("total",total);
        res.put("data",tGoods);
        return res;
    }

    /**
     *
     * @return
     */
    @GetMapping("/all")
    public List<TGoods> queryAll(){
        return tGoodsService.queryAll();
    }

    @GetMapping("/ElectronicsList")
    public List<TGoods> getElectronicsList(){
        return tGoodsService.getElectronicsList();
    }

    @GetMapping("/ApplianceList")
    public List<TGoods> getApplianceList(){
        return tGoodsService.getApplianceList();
    }

    @GetMapping("/ShowLifeList")
    public List<TGoods> getShowLifeList(){
        return tGoodsService.getShowLifeList();
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<TGoods> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.tGoodsService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param tGoods 实体
     * @return 新增结果
     */
    @PostMapping("{uid}")
    public ResponseEntity<TGoods> add(@PathVariable int uid,@RequestBody TGoods tGoods) {
        return ResponseEntity.ok(this.tGoodsService.insert(uid,tGoods));
    }

    /**
     * 编辑数据
     *
     * @param tGoods 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<TGoods> edit(@RequestBody  TGoods tGoods) {
        return ResponseEntity.ok(this.tGoodsService.update(tGoods));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.tGoodsService.deleteById(id));
    }

}

