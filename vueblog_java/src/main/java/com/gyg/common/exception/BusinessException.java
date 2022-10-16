package com.gyg.common.exception;

/**
 * @author ：吉金武
 * @ClassName BusinessException
 * @date ：2022/10/16 - 17:19
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description: 自定义异常类
 **/
public class BusinessException extends Throwable {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 描述
     */
    private String desc;

    public BusinessException(){
        super();
    }

    public BusinessException(int code,String desc){
        super();
        this.code=code;
        this.desc=desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

