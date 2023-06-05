package com.bs.store.controller;

import com.bs.store.common.JsonResult;
import com.bs.store.entity.TCollect;
import com.bs.store.entity.TGoods;
import com.bs.store.service.TCollectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (TCollect)表控制层
 *
 * @author makejava
 * @since 2023-04-07 15:25:23
 */
@RestController
@RequestMapping("tCollect")
public class TCollectController {
    /**
     * 服务对象
     */
    @Resource
    private TCollectService tCollectService;

    /**
     * 分页查询
     *
     * @param tCollect 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /**
     * 分页查询
     *
     * @param pageNum      当前页码
     * @param pageSize 数据个数
     * @return 查询结果
     */
    @GetMapping("/page")
    public Map<String,Object> queryByPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        long total = tCollectService.count();
        pageNum = (pageNum-1)*pageSize;
        List<TCollect> tCollects = tCollectService.queryAllByLimit(pageNum, pageSize);
        Map<String,Object> res = new HashMap<>();
        res.put("total",total);
        res.put("data",tCollects);
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
        long total = tCollectService.searchCount(searchKey);
        pageNum = (pageNum-1)*pageSize;
        List<TCollect> tCollects = tCollectService.searchByKey(searchKey,pageNum, pageSize);
        Map<String,Object> res = new HashMap<>();
        res.put("total",total);
        res.put("data",tCollects);
        return res;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<TCollect> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.tCollectService.queryById(id));
    }

    @GetMapping("/byUid/{uid}")
    public JsonResult queryByUid(@PathVariable Integer uid){
        List<TCollect> tCollects = tCollectService.queryByUid(uid);
        if(tCollects!=null){
            return new JsonResult("1","sucess",tCollects);
        }
        return new JsonResult("0","erorr",tCollects);
    }


    /**
     * 新增数据
     *
     * @param tCollect 实体
     * @return 新增结果
     */
    @PostMapping
    public JsonResult add(@RequestBody TCollect tCollect) {
        int row = tCollectService.insert(tCollect);
        if(row==-1){
            return new JsonResult("-1","该商品已收藏过了",null);
        }else if(row==1) {
            return new JsonResult("1","商品收藏成功",null);
        }else{
            return new JsonResult("500","系统错误",null);
        }

    }

    /**
     * 编辑数据
     *
     * @param tCollect 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<TCollect> edit(TCollect tCollect) {
        return ResponseEntity.ok(this.tCollectService.update(tCollect));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{id}")
    public JsonResult deleteById(@PathVariable  Integer id) {
        boolean b = tCollectService.deleteById(id);
        if (b){
            return new JsonResult("001","删除成功",null);
        }
        return new JsonResult("eroor","删除失败",null);
    }

}

