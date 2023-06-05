package com.bs.store.service.impl;

import com.bs.store.dao.TOrderDao;
import com.bs.store.dao.UserMapper;
import com.bs.store.entity.TCart;
import com.bs.store.entity.TOrder;
import com.bs.store.entity.TOrderItem;
import com.bs.store.dao.TOrderItemDao;
import com.bs.store.service.TOrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * (TOrderItem)表服务实现类
 *
 * @author makejava
 * @since 2022-11-07 17:19:49
 */
@Service("tOrderItemService")
public class TOrderItemServiceImpl implements TOrderItemService {
    @Resource
    private TOrderItemDao tOrderItemDao;
    @Resource
    private TOrderDao tOrderDao;
    @Resource
    private UserMapper userMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param Oid 主键
     * @return 实例对象
     */
    @Override
    public TOrderItem queryById(String Oid) {
        return this.tOrderItemDao.queryById(Oid);
    }

    @Override
    public List<TOrderItem> queryAll() {
        return tOrderItemDao.queryAll();
    }

    @Override
    public List<TOrderItem> queryAllByOid(String oid) {
        return tOrderItemDao.queryAllByOid(oid);
    }

    @Override
    public List<Date> queryCreatedTimeByOid(String oid) {
        return tOrderItemDao.queryCreatedTimeByOid(oid);
    }

    @Override
    public Map<String,Object>  queryAllByUid(int uid) {
        Map<String,Object> map = new HashMap<>();
        List<TOrderItem> items = tOrderItemDao.queryAllByUid(uid);
        List<String> oidList = new ArrayList<>();
        List<Object> dataList = new ArrayList<>();
        List<Object> sunNum = new ArrayList<>();
        List<Object> sumPrice = new ArrayList<>();
        for (int i = 0; i<items.size() ; i++) {
            oidList.add(items.get(i).getOid());
            dataList.add(items.get(i).getCreatedTime());
        }
        List<String> OnlyOid= oidList.stream().distinct().collect(Collectors.toList());
        List<Object> newdataList=dataList.stream().distinct().collect(Collectors.toList());
        String oid = "";
        List<Object> list =new ArrayList<>();
        for(int i = 0;i<OnlyOid.size();i++){
            oid = OnlyOid.get(i);
            List<TOrderItem> queryAllByOid = tOrderItemDao.queryAllByOid(oid);
            int totalNum=0;
            double totalPrice=0;
            for (int j = 0; j <queryAllByOid.size() ; j++) {
                totalNum += queryAllByOid.get(j).getNum();
                totalPrice +=queryAllByOid.get(j).getPrice();
            }
            list.add(queryAllByOid);
            sunNum.add(totalNum);
            sumPrice.add(totalPrice);
        }
        map.put("createTime",newdataList);
        map.put("oids",OnlyOid);
        map.put("data",list);
        map.put("totalNum",sunNum);
        map.put("totalPrice",sumPrice);
        return map;
    }

    @Override
    public List<TOrderItem> queryAllByLimit(Integer pageNum, Integer pageSize) {
        return tOrderItemDao.queryAllByLimit(pageNum,pageSize);
    }

    @Override
    public long count() {
        return tOrderItemDao.count();
    }

    @Override
    public List<TOrderItem> searchByKey(String searchKey, Integer pageNum, Integer pageSize) {
        return tOrderItemDao.searchByKey(searchKey, pageNum, pageSize);
    }

    @Override
    public long searchCount(String searchKey) {
        return tOrderItemDao.searchCount(searchKey);
    }



    /**
     * 新增数据
     *
     * @param tOrderItem 实例对象
     * @return 实例对象
     */
    @Override
    public TOrderItem insert(TOrderItem tOrderItem) {
        this.tOrderItemDao.insert(tOrderItem);
        return tOrderItem;
    }

    @Override
    public int addBatch(String oid,List<TCart> list) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
//        String prefixStr= sdf.format(new Date());
//        String suffixStr = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
//        String oid = prefixStr+suffixStr.toUpperCase();
        Date date = new Date();
        //订单详情
        int row =0;
        for (int i = 0; i <list.size() ; i++) {
            TCart tCart = list.get(i);
            TOrderItem item = new TOrderItem();
            item.setOid(oid);
            item.setPid(tCart.getPid());
            item.setUid(tCart.getUid());
            item.setTitle(tCart.getTitle());
            item.setImage(tCart.getImage());
            item.setPrice(tCart.getPrice());
            item.setNum(tCart.getNum());
            item.setCreatedTime(date);
            item.setModifiedTime(date);
            item.setCreatedUser(userMapper.selectUserById(tCart.getUid()).getUsername());
            item.setModifiedUser(userMapper.selectUserById(tCart.getUid()).getUsername());
            row = tOrderItemDao.insert(item);
            row+=row;
        }
        return row;
    }

    /**
     * 修改数据
     *
     * @param tOrderItem 实例对象
     * @return 实例对象
     */
    @Override
    public TOrderItem update(TOrderItem tOrderItem) {
        this.tOrderItemDao.update(tOrderItem);
        return this.queryById(tOrderItem.getOid());
    }

    /**
     * 通过主键删除数据
     *
     * @param oid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String oid) {
        return this.tOrderItemDao.deleteById(oid) > 0;
    }


    }
