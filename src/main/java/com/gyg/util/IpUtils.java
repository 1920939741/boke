package com.gyg.util;

import com.google.common.base.Strings;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author chengyiyong
 * @Date 2022/10/7 17:14
 **/
public class IpUtils {

    public static String getIpAddr(HttpServletRequest request) {
        String xIp = request.getHeader("X-Real-IP");
        String xFor = request.getHeader("X-Forwarded-For");

        if (!Strings.isNullOrEmpty(xFor) && !"unKnown".equalsIgnoreCase(xFor)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = xFor.indexOf(",");
            if (index != -1) {
                return xFor.substring(0, index);
            } else {
                return xFor;
            }
        }
        xFor = xIp;
        if (!Strings.isNullOrEmpty(xFor) && !"unKnown".equalsIgnoreCase(xFor)) {
            return xFor;
        }
        if (Strings.nullToEmpty(xFor).trim().isEmpty() || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("Proxy-Client-IP");
        }
        if (Strings.nullToEmpty(xFor).trim().isEmpty() || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (Strings.nullToEmpty(xFor).trim().isEmpty() || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (Strings.nullToEmpty(xFor).trim().isEmpty() || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (Strings.nullToEmpty(xFor).trim().isEmpty() || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getRemoteAddr();
        }


        return "0:0:0:0:0:0:0:1".equals(xFor) ? "127.0.0.1" : xFor;
    }

}
