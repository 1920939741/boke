package com.gyg.controller;


import com.gyg.common.lang.Result;
import com.gyg.entity.User;
import com.gyg.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 */
@RestController
@RequestMapping("/user")
public class UserController {

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
    @GetMapping("/save")
    public Result save(@Validated @RequestBody User user){
        return Result.success(user);
    }

}
