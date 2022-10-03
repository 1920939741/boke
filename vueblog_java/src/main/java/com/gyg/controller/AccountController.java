package com.gyg.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gyg.common.dto.LoginDto;
import com.gyg.common.lang.Result;
import com.gyg.entity.User;
import com.gyg.service.UserService;
import com.gyg.shiro.AccountProfile;
import com.gyg.shiro.JwtToken;
import com.gyg.util.JwtUtils;
import io.jsonwebtoken.Jwt;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.omg.CORBA.UnknownUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@RestController
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {

        System.out.println("用户名和密码:" + loginDto.getUsername() + " " + loginDto.getPassword());
//        获取到当前用户
        Subject subject = SecurityUtils.getSubject();
//        封装用户名和密码
        UsernamePasswordToken token = new UsernamePasswordToken(loginDto.getUsername(), loginDto.getPassword());

        System.out.println("封装用户名和密码成功！！！");

        try {
//            使用shiro进行用户验证
            subject.login(token);
//            如果验证通过再根据用户名查找到该用户
            User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
            Assert.notNull(user, "用户不存在！");

            if (!user.getPassword().equals(loginDto.getPassword())) {
                return Result.fail("密码错误！");
            }
//            根据用户id生成一个jwt
            String jwt = jwtUtils.generateToken(user.getId());

//            将jwt写入
            response.setHeader("authorization", jwt);
            response.setHeader("Access-Control-Expose-Headers", "authorization");

            //            如果正确就返回用户信息
            return Result.success(MapUtil.builder()
                    .put("id", user.getId())
                    .put("username", user.getUsername())
                    .put("avatar", user.getAvatar())
                    .put("email", user.getEmail())
                    .map()
            );
        } catch (UnknownAccountException e) {
            return Result.fail("用户不存在2");
        } catch (IncorrectCredentialsException e) {
            return Result.fail("密码不正确2");
        }
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
//        AccountProfile profile = (AccountProfile) subject.getPrincipal();
//        System.out.println(profile.getId());
//        会请求到logout
        subject.logout();

        return Result.success("退出成功");
    }

    @RequiresAuthentication
    @GetMapping("/testlogin")
    public Result testlogin() {
        User user = userService.getById(1L);
        return Result.success(user);
    }


}
