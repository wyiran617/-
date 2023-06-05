package com.bs.store.service.impl;

import com.bs.store.entity.User;
import com.bs.store.dao.UserMapper;
import com.bs.store.service.exception.ServiceException;
import com.bs.store.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class userServiceImpl implements userService {

    @Autowired
    private UserMapper mapper;

    @Override
    public Integer insert(User user) {
        return mapper.insert(user);
    }

    @Override
    public User findByUsername(String username) {
        return mapper.findByUsername(username);
    }

    @Override
    public int regUser(User u) {
        User result = mapper.findByUsername(u.getUsername());
        if (result!=null){
            throw new ServiceException("用户名已被占用");
        }
        u.setIsDelete(0);
        //补全数据:四个日志字段信息
        u.setCreatedUser(u.getUsername());
        u.setModifiedUser(u.getUsername());
        Date date = new Date();//java.util.Date
        u.setCreatedTime(date);
        u.setModifiedTime(date);

        Integer rows = mapper.insert(u);
        if(rows !=1){
            throw new ServiceException("在用户注册中发生异常");
        }
        return rows;
    }

    @Override
    public List<User> selectAllUsers() {
        return mapper.selectAllUsers();
    }

    @Override
    public User selectUserById(int id) {
        return mapper.selectUserById(id);
    }

    @Override
    public int updateByPrimaryKey(User user) {
        return mapper.updateByPrimaryKey(user);
    }

    @Override
    public int setUserPassword(User user) {
        return mapper.setUserPassword(user);
    }

    @Override
    public int deleteByPrimaryKey(int id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public User LoginUser(User user) {
        return mapper.LoginUser(user);
    }


    private String getMD5Password(String password,String salt) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

}
