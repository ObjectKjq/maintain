package com.kjq.utils;

import com.kjq.enums.StatusCodeEnum;
import lombok.Data;

//封装返回的消息
@Data
public class FFResult<T> {
    //状态码
    private int code;
    //对于返回信息的描述
    private String message;
    //返回的消息对象
    private T data;

    public FFResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public FFResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //返回成功不带数据
    public static <T> FFResult<T> success(StatusCodeEnum resultCode){
        return new FFResult<>(resultCode.code(), resultCode.message());
    }

    //返回成功带数据
    public static <T> FFResult<T> success(StatusCodeEnum resultCode, T data){
        return new FFResult<>(resultCode.code(), resultCode.message(), data);
    }

    //报错不带数据
    public static <T> FFResult<T> error(StatusCodeEnum resultCode){
        return new FFResult<>(resultCode.code(), resultCode.message());
    }

    //报错带数据
    public static <T> FFResult<T> error(StatusCodeEnum resultCode, T data){
        return new FFResult<>(resultCode.code(), resultCode.message(), data);
    }
}
