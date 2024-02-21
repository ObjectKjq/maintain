package com.kjq.enums;

public enum StatusCodeEnum {
    JURISDICTION_ERROR(40300, "没有权限"),
    SUCCESS(20000, "操作成功"),
    ERROR(40000, "失败"),
    ERROR_AUTHENTICATION(40100, "没有登陆");
    private final Integer code;
    private final String message;
    StatusCodeEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
    public Integer code(){
        return this.code;
    }
    public String message(){
        return this.message;
    }
}
