package com.gyg.core.interceptor;
import com.gyg.core.context.DataContextHolder;
import com.gyg.entity.User;
import com.gyg.util.DateUtils;
import com.gyg.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 *
 */
@Slf4j
public class FirstInterceptor extends HandlerInterceptorAdapter {

    private static String REQUEST_START_TIME = "APP.CONTEX.REQUEST_START_TIME";
    private static String REQUEST_THRED_NAME = "APP.CONTEX.REQUEST_THRED_NAME";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        request.getSession();
        String name = request.getRequestURI().replaceAll("/+", "-");
        if (name.startsWith("-")) {
            name = name.replaceFirst("-", "");
        }
        int length = 35;
        if (name.length() < length) {
            String prefix = String.join("", Collections.nCopies(length - name.length(), " "));
            name = prefix + name;
        }
        DataContextHolder.setContext(REQUEST_THRED_NAME, Thread.currentThread().getName());
        Thread.currentThread().setName(name + "-" + DateUtils.dateToString(new Date(), "mm:ss:SSS"));
        DataContextHolder.setContext(REQUEST_START_TIME, System.currentTimeMillis());
        String logMes = String.format("--IP:[%s]--%s", IpUtils.getIpAddr(request), request.getRequestURI());
        log.warn(logMes);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        Object attribute = DataContextHolder.getContext(REQUEST_START_TIME);
        if (attribute == null) {
            return;
        }
        long startTime = (long) attribute;
        long now = System.currentTimeMillis();
        long time = now - startTime;
        Integer userId = null;
        String userName = "";
        if (request.getSession() != null && request.getSession().getAttribute("USER") != null) {
            User user = (User) request.getSession().getAttribute("USER");
            userName = user.getUsername();
        }
        String logMes = String.format("[%s]--IP:[%s]--%s--耗时:%s Millis", userName, IpUtils.getIpAddr(request), request.getRequestURI(), time);
        log.warn(logMes + (time / 1000 == 0 ? "" : " More-than-" + time / 1000 + "-sec"));
        Object context = DataContextHolder.getContext(REQUEST_THRED_NAME);
        if (context != null) {
            Thread.currentThread().setName((String) context);
        } else {
            Random rand = new Random();
            Thread.currentThread().setName("http-nio-80-exec-" + (rand.nextInt(50) + 1));
        }
    }
}
