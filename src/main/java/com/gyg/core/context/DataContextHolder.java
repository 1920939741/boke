package com.gyg.core.context;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import sun.util.locale.LocaleUtils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @Author chengyiyong
 * @Date 2022/10/26 11:03
 **/
public class DataContextHolder {
    private static final ThreadLocal<Map<String, Object>> ctx = new ThreadLocal<Map<String, Object>>() {
        protected synchronized HashMap<String, Object> initialValue() {
            return new HashMap();
        }
    };

    public DataContextHolder() {
    }

    public static Map<String, Object> getDataContext() {
        return (Map)ctx.get();
    }

    public static void setContext(String key, Object value) {
        getDataContext().put(key, value);
    }

    public static Object getContext(String key) {
        return getDataContext().get(key);
    }
}
