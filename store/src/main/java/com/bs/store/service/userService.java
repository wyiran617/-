package com.bs.store.service;

import com.bs.store.entity.User;

import java.util.List;



public interface userService {

    Integer insert(User user);

    User findByUsername(String username);

    int regUser(User u);

    List<User> selectAllUsers();

    User selectUserById(int id);

    int updateByPrimaryKey(User user);

    int setUserPassword(User user);

    int deleteByPrimaryKey(int id);

    User LoginUser(User user);
}
