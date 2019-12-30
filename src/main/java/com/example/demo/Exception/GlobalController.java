package com.example.demo.Exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: demo
 * @description: 使用 @ ExceptionHandler 注解
 * @author: xiuchao Song
 * @create: 2019-12-30 13:49
 **/
@Controller
public class GlobalController {

    /**
     * 用于处理异常的
     * @return
     */
    @ExceptionHandler({Exception.class})
    public String exception(Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
        return "exception";
    }

    @RequestMapping("test")
    public void test() throws Exception {
        throw new Exception("出错了！");
    }
}