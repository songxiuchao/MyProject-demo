/*
package com.example.demo.Exception;

import com.example.demo.Common.constant.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

*/
/**
 * @program: demo
 * @description: 继承 ResponseEntityExceptionHandler 类来实现针对 Rest 接口 的全局异常捕获，并且可以返回自定义格式：
 * @author: xiuchao Song
 * @create: 2019-12-30 13:55
 **//*

@Slf4j
@ControllerAdvice
public class ExceptionHandlerBean  extends ResponseEntityExceptionHandler {

    */
/**
     * 数据找不到异常
     * @param ex
     * @param request
     * @return
     * @throws IOException
     *//*

    @ExceptionHandler({DataNotFoundException.class})
    public ResponseEntity<Object> handleDataNotFoundException(RuntimeException ex, WebRequest request) throws IOException {
        return getResponseEntity(ex,request, ResponseCode.ERROR_999);
    }

    */
/**
     * 根据各种异常构建 ResponseEntity 实体. 服务于以上各种异常
     * @param ex
     * @param request
     * @param specificException
     * @return
     *//*

    private ResponseEntity<Object> getResponseEntity(RuntimeException ex, WebRequest request, ReturnStatusCode specificException) {

        ReturnTemplate returnTemplate = new ReturnTemplate();
        returnTemplate.setStatusCode(specificException);
        returnTemplate.setErrorMsg(ex.getMessage());

        return handleExceptionInternal(ex, returnTemplate,
                new HttpHeaders(), HttpStatus.OK, request);
    }

}*/
