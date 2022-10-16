package com.gyg.common.lang;


import lombok.Data;

import java.io.Serializable;

/**
 * 封装一个返回统一格式数据的结果集
 */
@Data
public class Result implements Serializable {

    private int code;   //200正常、非200异常
    private String message;     //提示信息
    private Object data;    //返回数据

    public Result(int code, String message, Object data){
        this.code=code;
        this.message=message;
        this.data=data;
    }

    public Result() {

    }

    public Result(int code, String message) {
        this.code=code;
        this.message=message;
    }

    public static Result success(Object data) {
        return success(200,"操作成功",data);
    }


    public static Result success() {
        return success(200,"操作成功",null);
    }

    /**
     * 消息返回方法
     *
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static Result success(int code, String message, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMessage(message);
        r.setData(data);
        return r;
    }


    public static Result fail(String message) {
        return fail(400,message,null);
    }

    public static Result fail(String message, Object data) {
        return fail(400,message,data);
    }

    /**
     * 异常结果返回
     * @param code
     * @param message
     * @return
     */
    public static Result error(int code,String message){
        return new Result(code,message);
    }

    public static Result fail(int code, String message, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMessage(message);
        r.setData(data);
        return r;
    }

    public static Result error(int code, String message, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMessage(message);
        r.setData(data);
        return r;
    }

}
