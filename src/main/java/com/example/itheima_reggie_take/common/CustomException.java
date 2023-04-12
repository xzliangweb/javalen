package com.example.itheima_reggie_take.common;

/**
 * 自定义业务异常类
 */
public class CustomException extends RuntimeException{
    public CustomException(String message) {
        super(message);
    }
}
