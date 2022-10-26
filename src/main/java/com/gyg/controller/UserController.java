package com.gyg.controller;


import com.gyg.common.AssertionRequestContext;
import com.gyg.common.comtent.ContentCont;
import com.gyg.common.exception.BusinessException;
import com.gyg.common.lang.Result;
import com.gyg.entity.User;
import com.gyg.service.UserService;
import com.gyg.util.MD5Util;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
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
        User user= userService.getById(3L);
        HttpSession session = AssertionRequestContext.getSession();
        //把用户信息存在session中
        session.setAttribute(ContentCont.CURRENT_SESSION_USER,user);
        return Result.success(user);
    }

    /**
     * 测试输入数据是否规范的校验
     * @param user
     * @return
     */
    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String encryptedPwd = MD5Util.getEncryptedPwd(defaultPassword);
        user.setPassword(encryptedPwd);
        user.setStatus(0);
        user.setCreated(new Timestamp(new Date().getTime()));
        user.setLastLogin(new Timestamp(new Date().getTime()));
        boolean save = userService.save(user);
        return Result.success(save);
    }

    @ApiOperation(value = "根据账号查询")
    @ApiImplicitParam(name = "username", value = "账号")
    @GetMapping("/findByUserName")
    public Result findByUserName(String username){
        return Result.success(userService.findByUserName(username));
    }

    @ApiOperation(value = "根据邮箱查询")
    @ApiImplicitParam(name = "email", value = "邮箱", example = "xxxxxx@qq.com")
    @GetMapping("/findByEmail")
    public Result findByEmail(String email){
        return Result.success(userService.findByEmail(email));
    }

    @ApiOperation(value = "邮箱发送验证码")
    @ApiImplicitParam(name = "email", value = "邮箱", example = "xxxxxx@qq.com")
    @PostMapping("/sendEmailCode")
    public  Result sendEmailCode(String email) throws GeneralSecurityException, MessagingException {
        return userService.sendEmailCode(email);
    }

    @ApiOperation(value = "验证邮箱验证码")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "code", value = "验证码", example = "123456"),
            @ApiImplicitParam(name = "email",value = "邮箱",example = "xxxxx@qq.com")
    })
    @PostMapping("/verifyEmailCode")
    public  Result verifyEmailCode(String email,String code) throws Exception {
        return Result.success(userService.verifyEmailCode(email,code));
    }

    @ApiOperation(value = "检查原密码")
    @ApiImplicitParam(name = "password", value = "原密码")
    @PostMapping("/checkPassword")
    public Result checkPassword(String password) throws Exception {
        return Result.success(userService.checkPassword(password));
    }

    @ApiOperation(value = "修改密码")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "email",value = "邮箱"),
            @ApiImplicitParam(name = "newPassword",value = "新密码"),
            @ApiImplicitParam(name = "confirmPassword",value = "确认密码")
    })
    @PostMapping("/updatePassword")
    public Result updatePassword(String email,String newPassword,String confirmPassword) throws BusinessException {
        return Result.success(userService.updatePassword(email,newPassword,confirmPassword));
    }
}
