package com.gyg.util;

import cn.hutool.core.bean.BeanUtil;
import com.gyg.entity.User;
import com.gyg.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * shiro工具类
 */
public class ShiroUtil {

    /**
     * 获取到当前用户
     * @return
     */
    public static AccountProfile getProfile(){
        System.out.println("进入工具类");
//        AccountProfile accountProfile = new AccountProfile();
        AccountProfile accountProfile =  (AccountProfile) SecurityUtils.getSubject().getPrincipal();
        System.out.println(accountProfile);
        System.out.println("user.toString()==================");
//        System.out.println(user.toString());
        return accountProfile;
    }

}
