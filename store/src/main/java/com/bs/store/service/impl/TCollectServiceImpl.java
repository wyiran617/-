package com.bs.store.service.impl;

import com.bs.store.dao.UserMapper;
import com.bs.store.entity.TCollect;
import com.bs.store.dao.TCollectDao;
import com.bs.store.service.TCollectService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (TCollect)表服务实现类
 *
 * @author makejava
 * @since 2023-04-07 15:25:23
 */
@Service("tCollectService")
public class TCollectServiceImpl implements TCollectService {
    @Resource
    private TCollectDao tCollectDao;
    @Resource
    private UserMapper userMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param cid 主键
     * @return 实例对象
     */
    @Override
    public TCollect queryById(Integer cid) {
        return this.tCollectDao.queryById(cid);
    }

    @Override
    public TCollect queryByGid(Integer id) {
        return tCollectDao.queryByGid(id);
    }

    @Override
    public List<TCollect> queryByUid(Integer uid) {
        return tCollectDao.queryByUid(uid);
    }

    @Override
    public List<TCollect> searchByKey(String searchKey, Integer pageNum, Integer pageSize) {
        return tCollectDao.searchByKey(searchKey, pageNum, pageSize);
    }

    @Override
    public long searchCount(String searchKey) {
        return tCollectDao.searchCount(searchKey);
    }

    @Override
    public List<TCollect> queryAllByLimit(Integer pageNum, Integer pageSize) {
        return tCollectDao.queryAllByLimit(pageNum, pageSize);
    }

    @Override
    public long count() {
        return tCollectDao.count();
    }



    /**
     * 新增数据
     *
     * @param tCollect 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TCollect tCollect) {
        TCollect queryByGid = tCollectDao.queryByGid(tCollect.getId());
        if(queryByGid!=null){
            return -1;//该商品已被收藏
        }else{
            tCollect.setCreatedUser(userMapper.selectUserById(tCollect.getUid()).getUsername());
            tCollect.setModifiedUser(userMapper.selectUserById(tCollect.getUid()).getUsername());
            Date date = new Date();//java.util.Date
            tCollect.setCreatedTime(date);
            tCollect.setModifiedTime(date);

            return tCollectDao.insert(tCollect);
        }
    }

    /**
     * 修改数据
     *
     * @param tCollect 实例对象
     * @return 实例对象
     */
    @Override
    public TCollect update(TCollect tCollect) {
        this.tCollectDao.update(tCollect);
        return this.queryById(tCollect.getCid());
    }

    /**
     * 通过主键删除数据
     *
     * @param cid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer cid) {
        return this.tCollectDao.deleteById(cid) > 0;
    }
}
