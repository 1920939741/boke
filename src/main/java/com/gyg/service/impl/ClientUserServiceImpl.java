package com.gyg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyg.common.AssertionRequestContext;
import com.gyg.common.comtent.KeyContentCont;
import com.gyg.common.exception.BusinessException;
import com.gyg.common.lang.Result;
import com.gyg.entity.User;
import com.gyg.ext.RedisOperationAware;
import com.gyg.mapper.ClientUserMapper;
import com.gyg.service.ClientUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @description: TODO
 * @author chengyiyong
 * @date 2022/12/20 11:00
 * @version 1.0
 */
@Slf4j
@Service
public class ClientUserServiceImpl extends ServiceImpl<ClientUserMapper, User> implements ClientUserService {


    @Autowired
    private ClientUserMapper userMapper;

    @Autowired
    private RedisOperationAware redisOperationAware;

    @Value("${spring.mail.username}")
    private String mailUserName;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public User findByUserName(String username) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username",username);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User findByEmail(String email) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("email",email);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public Result sendEmailCode(String email){
        String code = sendRandomCode();
        try {
            boolean qqEmailCorrect = isQQEmailCorrect(email);
            if (!qqEmailCorrect) {
                return Result.error(508,"邮箱不合法，请重新输入!");
            }
            if (redisOperationAware.exist(KeyContentCont.EMAIL_CODE_PREFIX+"_"+email)) {
                return Result.error(508,"请求过于频繁，请60秒后从新发送!");
            } else {
                //把邮箱验证码存到redis中过期时间为60秒
                log.info(KeyContentCont.EMAIL_CODE_PREFIX+"_"+email+"->{}", code);
                redisOperationAware.setEXNX(KeyContentCont.EMAIL_CODE_PREFIX+"_"+email, code, 60, TimeUnit.SECONDS);
            }
            SimpleMailMessage message = new SimpleMailMessage();
            //邮件发送人
            message.setFrom(mailUserName);
            //邮件接收人
            message.setTo(email);
            //邮件标题
            message.setSubject("boke账号安全验证");
            //邮件内容
            message.setText("亲爱的boke用户" + email + "，你好！您的验证码是：" + code + ",此验证码用于验证身份，修改密码密保等。请勿将验证码透露给其他人。" +
                    "本邮箱由系统自动发送，请勿直接回复！，感谢您的访问，祝您使用愉快！");
            //发送
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(500,e.getMessage());
        }
        return Result.success(code);
    }

    @Override
    public boolean verifyEmailCode(String email,String code)throws Exception {
        String emailCode = redisOperationAware.get(KeyContentCont.EMAIL_CODE_PREFIX+"_"+email, String.class);
        log.info("emailCode->{}",emailCode);
        if (emailCode == null){
            throw new Exception("请发送验证码!");
        } else if (emailCode.equals(code)){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkPassword(String password)throws Exception {
        Long userID = AssertionRequestContext.getUserID();
        if (userID != null){
            User user = userMapper.selectById(userID);
            if (user.getPassword().equals(password)){
                return true;
            }
        }else {
            throw new Exception("请先登录!");
        }
        return false;
    }

    @Override
    public Integer updatePassword(String email,String newPassword, String confirmPassword) throws BusinessException{
        if (confirmPassword.equals(newPassword)) {
            User user = new User();
            //todo 密码加密
            //String encryptedPwd = MD5Util.getEncryptedPwd(confirmPassword);
            user.setPassword(confirmPassword);
            user.setLastLogin(new Timestamp(new Date().getTime()));
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("email",email);
            return userMapper.update(user,wrapper);
        } else {
            throw new BusinessException(500, "新密码跟确认密码不一致，请重新输入!");
        }
    }


    /**
     * 判断qq邮箱格式是否正确
     *
     * @param email QQ邮箱
     * @return boolean
     */
    public boolean isQQEmailCorrect(String email) {
        String regex="([a-z][\\w]{5,17}[@][0-9a-z]{2,10}[\\.](com|cn|net|org|))||([1-9][0-9]{4,14}@(qq|QQ).(COM|com))";
        return email.matches(regex);
    }

    /**
     * 随机产生一个六位数的验证码
     *
     * @return String
     */
    public String sendRandomCode() {
        Random r = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            code += r.nextInt(10);
        }
        return code;
    }

}
