package com.example.demo.Exception;

import com.example.demo.Common.constant.ResponseCode;
import com.example.demo.Common.constant.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @program: demo
 * @description: 使用 @ControllerAdvice+ @ ExceptionHandler 注解
 * @author: xiuchao Song
 * @create: 2019-12-30 13:51
 **/
@ControllerAdvice
@ResponseBody
public class WebExceptionHandle {
    private static Logger logger = LoggerFactory.getLogger(WebExceptionHandle.class);
    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("参数解析失败", e);
        return Result.failure(ResponseCode.ERROR_DATA_VALIDATOR,"参数解析失败");
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("不支持当前请求方法", e);
        return Result.failure(ResponseCode.ERROR_DATA_VALIDATOR,"不支持当前请求方法");
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error("不支持当前媒体类型", e);
        return Result.failure(ResponseCode.ERROR_DATA_VALIDATOR,"不支持当前媒体类型");
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
//        if (e instanceof BusinessException){
//            return Result.failure(ResponseCode.ERROR_DATA_VALIDATOR,"参数解析失败");
//        }

        logger.error("服务运行异常", e);
        e.printStackTrace();
        return Result.failure(ResponseCode.ERROR_DATA_VALIDATOR,"服务运行异常");
    }
}