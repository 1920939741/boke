package com.gyg.util;

import com.gyg.common.exception.BusinessException;

/**
 * @author ：吉金武
 * @ClassName AssertUtil
 * @date ：2022/10/16 - 18:27
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description: 断言工具类
 **/
public class AssertUtil {
    public static void state (boolean expression,String message) throws BusinessException {
        if (!expression){
            throw new BusinessException(500,message);
        }
    }
}

