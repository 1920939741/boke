package com.gyg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyg.common.comtent.KeyContentCont;
import com.gyg.common.lang.Result;
import com.gyg.entity.User;
import com.gyg.ext.RedisOperationAware;
import com.gyg.mapper.UserMapper;
import com.gyg.service.UserService;
import com.sun.mail.util.MailSSLSocketFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * <p>
 * InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author 关注公众号：码猿编程日记
 * @since 2021-09-21
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisOperationAware redisOperationAware;

    @Value("${spring.mail.username}")
    private String mailUserName;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    @Override
    public Result sendEmailCode(String email){
        String code = sendRandomCode();
        try {
            boolean qqEmailCorrect = isQQEmailCorrect(email);
            if (!qqEmailCorrect) {
//                throw new Exception("邮箱不合法，请重新输入!");
                return Result.error(508,"邮箱不合法，请重新输入!");
            }
            if (redisOperationAware.exist(KeyContentCont.EMAIL_CODE_PREFIX+"_"+email)) {
//                throw new Exception("请求过于频繁，请60秒后从新发送!");
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

    /**
     * 判断qq邮箱格式是否正确
     *
     * @param email QQ邮箱
     * @return boolean
     */
    public boolean isQQEmailCorrect(String email) {
//        String regex = "[1-9]\\d{7,9}@(qq|QQ).(COM|com)";
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
