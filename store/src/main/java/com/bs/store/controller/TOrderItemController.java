package com.bs.store.controller;

import com.bs.store.common.JsonResult;
import com.bs.store.entity.TCart;
import com.bs.store.entity.TOrderItem;
import com.bs.store.service.TOrderItemService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (TOrderItem)表控制层
 *
 * @author makejava
 * @since 2022-11-07 17:19:49
 */
@RestController
@CrossOrigin
@RequestMapping("tOrderItem")
public class TOrderItemController {
    /**
     * 服务对象
     */
    @Resource
    private TOrderItemService tOrderItemService;


    @GetMapping("/all")
    private JsonResult queryAll(){
        List<TOrderItem> itemList = tOrderItemService.queryAll();
        return new JsonResult("001","查询成功",itemList);
    }

    @GetMapping("/allByUid/{uid}")
    private JsonResult queryAllByUid(@PathVariable Integer uid){
        Map<String, Object> map = tOrderItemService.queryAllByUid(uid);
        return new JsonResult("001","查询成功",map);
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
        long total = tOrderItemService.count();
        pageNum = (pageNum-1)*pageSize;
        List<TOrderItem> TOrderItemList = tOrderItemService.queryAllByLimit(pageNum, pageSize);
        Map<String,Object> res = new HashMap<>();
        res.put("total",total);
        res.put("data",TOrderItemList);
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
        long total = tOrderItemService.searchCount(searchKey);
        pageNum = (pageNum-1)*pageSize;
        List<TOrderItem> TOrderItems = tOrderItemService.searchByKey(searchKey,pageNum, pageSize);
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
    public ResponseEntity<TOrderItem> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.tOrderItemService.queryById(id));
    }


    @PostMapping
    public ResponseEntity<TOrderItem> add(@RequestBody TOrderItem orderItem) {
        return ResponseEntity.ok(this.tOrderItemService.insert(orderItem));
    }


    /**
     *
     * @param
     * @param goodsList
     * @return
     */
    @PostMapping("/addBatch/{oid}")
    public JsonResult  addBatch(@PathVariable String oid,@RequestBody List<TCart> goodsList) {
        int row = tOrderItemService.addBatch(oid,goodsList);
        if (row>0){
            return new JsonResult("001","结算成功",row);
        }else{
            return new JsonResult("erorr","结算失败",row);
        }
    }

    /**
     * 编辑数据
     *
     * @param tOrderItem 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<TOrderItem> edit(TOrderItem tOrderItem) {
        return ResponseEntity.ok(this.tOrderItemService.update(tOrderItem));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable String id) {
        return ResponseEntity.ok(this.tOrderItemService.deleteById(id));
    }

}

