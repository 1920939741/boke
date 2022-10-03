package com.gyg.common.lang;


import lombok.Data;

import java.io.Serializable;

/**
 * 封装一个返回统一格式数据的结果集
 */
@Data
public class Result implements Serializable {

    private int code;   //200正常、非200异常
    private String msg;     //提示信息
    private Object data;    //返回数据

    public static Result success(Object data) {
        return success(200,"操作成功",data);
    }

    /**
     * 消息返回方法
     *
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static Result success(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }


    public static Result fail(String msg) {
        return fail(400,msg,null);
    }

    public static Result fail(String msg, Object data) {
        return fail(400,msg,data);
    }

    public static Result fail(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

}
