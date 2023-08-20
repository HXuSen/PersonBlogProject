package com.hxsstu.exception;

import com.hxsstu.enums.AppHttpCodeEnum;

/**
 * ClassName: SystemException
 * Package: com.hxsstu.exception
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/20-17:27
 */
public class SystemException extends RuntimeException{

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }

}
