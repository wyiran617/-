package com.bs.store.dao;

import com.bs.store.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    /**
     * 新增用户
     * @param user 用户的数据
     * @return 受影响的行数
     */
    Integer insert(User user);

    /**
     * 根据用户名来查询用户数据
     * @param username
     * @return 返回查询到的用户结果
     */
    User findByUsername(String username);

    /**
     * 查询所有用户信息
     * @return
     */
    List<User> selectAllUsers();

    /**
     * 根据id查询用户
     */
    User selectUserById(int id);

    /**
     * 修改用户
     */
    int updateByPrimaryKey(User user);

    int setUserPassword(User user);

    /**
     * 删除用户
     */
    int deleteByPrimaryKey(int id);

    /**
     * 用户登录
     * @param user
     * @return
     */
    User LoginUser(User user);
}
