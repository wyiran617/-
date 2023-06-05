package com.bs.store.controller;

import com.bs.store.common.JsonResult;
import com.bs.store.entity.TCart;
import com.bs.store.service.TCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (TCart)表控制层
 *
 * @author makejava
 * @since 2022-11-07 17:18:49
 */
@RestController
@RequestMapping("tCart")
public class TCartController {
    /**
     * 服务对象
     */
    @Resource
    private TCartService tCartService;


    /**
     * 分页查询
     *
     * @param pageNum 当前页码
     * @param pageSize    数据个数
     * @return 查询结果
     */
    @GetMapping("/page")
    public Map<String,Object> queryByPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        long total = tCartService.count();
        pageNum = (pageNum-1)*pageSize;
        List<TCart> tCartList = tCartService.queryAllByLimit(pageNum, pageSize);
        Map<String,Object> res = new HashMap<>();
        res.put("total",total);
        res.put("data",tCartList);
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
        long total = tCartService.searchCount(searchKey);
        pageNum = (pageNum-1)*pageSize;
        List<TCart> tCarts = tCartService.searchByKey(searchKey,pageNum, pageSize);
        Map<String,Object> res = new HashMap<>();
        res.put("total",total);
        res.put("data",tCarts);
        return res;
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<TCart> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.tCartService.queryById(id));
    }

    @GetMapping("/byUid/{uid}")
    public JsonResult queryByUid(@PathVariable("uid") Integer uid){
        List<TCart> cartList = tCartService.queryByUid(uid);
        if(cartList!=null){
            return  new JsonResult("001","查询成功",cartList);
        }
        return  new JsonResult("000","未查询到信息",null);
    }

    /**
     * 新增数据
     *
     * @param tCart 实体
     * @return 新增结果
     */
    @PostMapping
    public JsonResult add(@RequestBody TCart tCart) {
        int row = tCartService.insert(tCart);
        List<TCart> cartList = tCartService.queryByUid(tCart.getUid());
        if(row==-1){
            return new JsonResult("-1","该商品已在购物车，数量+1",cartList);
        }else if(row ==1){
            return new JsonResult("001","加入购物车成功",cartList);
        }
        return new JsonResult("500","系统错误",null);
    }

    @PutMapping("/ByPidAndUid")
    public JsonResult editByUidAndPid(@RequestBody TCart tCart){
        if(tCart!=null){
            int row = tCartService.updateByPidAndUid(tCart);
            if(row>0){
                List<TCart> cartList = tCartService.queryByUid(tCart.getUid());
                return new JsonResult("001","更新数据成功",cartList);
            }else {
                return new JsonResult("eroor","更新数据失败",null);
            }
        }else{
            return new JsonResult("500","tCart参数为空",null);
        }
    }

    /**
     * 编辑数据
     *
     * @param tCart 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<TCart> edit(TCart tCart) {
        return ResponseEntity.ok(this.tCartService.update(tCart));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{id}")
    public JsonResult deleteById(@PathVariable Integer id) {
        boolean b = tCartService.deleteById(id);
        if(b){
            return new JsonResult("001","删除成功",null);
        }else {
            return new JsonResult("erorr","删除失败",null);
        }

    }

}

