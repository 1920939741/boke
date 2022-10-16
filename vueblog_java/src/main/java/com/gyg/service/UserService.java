package com.gyg.service;

import com.gyg.common.exception.BusinessException;
import com.gyg.common.lang.Result;
import com.gyg.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * InnoDB free: 11264 kB 服务类
 * </p>
 *
 * @author 关注公众号：码猿编程日记
 * @since 2021-09-21
 */
public interface UserService extends IService<User> {

    User findByUserName(String username);

    User findByEmail(String email);

    Result sendEmailCode(String email) throws GeneralSecurityException, MessagingException;

    boolean verifyEmailCode(String email,String code) throws Exception;

    boolean checkPassword(String password) throws Exception;

    Integer updatePassword(String newPassword, String confirmPassword) throws BusinessException, Exception;
}
