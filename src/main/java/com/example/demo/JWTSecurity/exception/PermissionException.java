package com.example.demo.JWTSecurity.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: demo
 * @description: 没有访问权限处理类
 * @author: xiuchao Song
 * @create: 2019-11-05 15:39
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionException extends RuntimeException{
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String errorMessage;

}
