package com.gyg.controller;


import com.gyg.common.lang.Result;
import com.gyg.entity.User;
import com.gyg.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 测试接口
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${default_password}")
    private String defaultPassword;

    @Autowired
    UserService userService;

    /**
     * @RequiresAuthentication  指定需要登录认证才能进行的请求
     * @return
     */
//    @RequiresAuthentication
    @GetMapping("/index")
    public Result index(){
        User user= userService.getById(1L);
        return Result.success(user);
    }

    /**
     * 测试输入数据是否规范的校验
     * @param user
     * @return
     */
    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user){
        user.setPassword(defaultPassword);
        user.setStatus(0);
        user.setCreated(new Timestamp(new Date().getTime()));
        user.setLastLogin(new Timestamp(new Date().getTime()));
        boolean save = userService.save(user);
        return Result.success(save);
    }

    @GetMapping("/findByUserName")
    public Result findByUserName(String username){
        return Result.success(userService.findByUserName(username));
    }

    @GetMapping("/findByEmail")
    public Result findByEmail(String email){
        return Result.success(userService.findByEmail(email));
    }

}
