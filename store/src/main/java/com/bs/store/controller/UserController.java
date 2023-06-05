package com.bs.store.controller;

import com.bs.store.common.JsonResult;
import com.bs.store.entity.User;
import com.bs.store.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController  {

    @Autowired
    private userService service;

    /**
     * 用户登录
     * @return
     */

    @PostMapping("/login")
    public JsonResult Login(@RequestBody User user){

        User loginUser = service.LoginUser(user);
        if(loginUser!=null){
            return new JsonResult("200","登录成功",loginUser);
        }else{
            return JsonResult.error();
        }
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @PostMapping("/reg")
    public int reg(@RequestBody User user){
        System.out.println(user.toString());
        return service.regUser(user);
    }

    /**
     * 根据name查询用户
     * @param username
     * @return
     */
    @GetMapping("/username")
    public JsonResult findByUsername(@RequestParam String username){
        User findUser = service.findByUsername(username);
        if(findUser!=null){
            return new JsonResult("0","已存在该用户",findUser);
        }else{
            return new JsonResult("1","该用户名可以使用", null);
        }
    }

    /**
     * 无条件查询所有用户
     * @return
     */
    @RequestMapping("/all")
    public List<User> selectAllUsers(){
        return service.selectAllUsers();
    }

    /**
     * 根据id查询用户
     */
    @RequestMapping("/{uid}")
    public User selectById(@PathVariable int uid) {
        return service.selectUserById(uid);
    }

    /**
     * 更新用户
     */
    @PutMapping("/update")
    public int updateUser(@RequestBody User user){
        return  service.updateByPrimaryKey(user);
    }


    /**
     * 更新用户密码
     */
    @PutMapping("/password")
    public int setUserPassword(@RequestBody User user){
        return  service.setUserPassword(user);
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/delete/{param}")//根据id删除user
    public int deleteUser( @PathVariable int param){
        return service.deleteByPrimaryKey(param);
    }
}
