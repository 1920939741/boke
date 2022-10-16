package com.gyg.common;

import com.gyg.common.comtent.ContentCont;
import com.gyg.entity.User;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author ：吉金武
 * @ClassName AssertionRequestContext
 * @date ：2022/10/16 - 13:21
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description: 通过request上下文获取用户信息
 **/
public class AssertionRequestContext {

    public static HttpServletRequest getHttpServlet(){
        ServletRequestAttributes  requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }

    /**
     * 获取HttpSession对象
     * @return
     */
    public static HttpSession getSession(){
        ServletRequestAttributes  requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest().getSession();
    }

    /**
     * 获取当前用户信息
     * @return
     */
    public static User getUserInfo(){
        return (User)getSession().getAttribute(ContentCont.CURRENT_SESSION_USER);
    }

    /**
     * 获取当前用户ID
     * @return
     */
    public static Long getUserID(){
        return getUserInfo() != null ? getUserInfo().getId() : null;
    }

    /**
     * 获取当前用户名
     * @return
     */
    public static String getUserName(){
        return getUserInfo() != null ? getUserInfo().getUsername() : null;
    }
}

