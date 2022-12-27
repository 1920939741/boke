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
 * @description: TODO
 * @author chengyiyong
 * @date 2022/12/20 10:58
 * @version 1.0
 */
public interface ClientUserService extends IService<User> {

    User findByUserName(String username);

    User findByEmail(String email);

    Result sendEmailCode(String email) throws GeneralSecurityException, MessagingException;

    boolean verifyEmailCode(String email,String code) throws Exception;

    boolean checkPassword(String password) throws Exception;

    Integer updatePassword(String email,String newPassword, String confirmPassword) throws BusinessException;
}
