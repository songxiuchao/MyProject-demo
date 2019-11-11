package com.example.demo.Common.constant;

import java.io.Serializable;

/**
 * @program: 数据返回对象
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-09 11:49
 **/
public class Result implements Serializable{

    private static final long serialVersionUID = -1551643854488314532L;

    private String code;

        private String msg;

        private Object data;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        /****************************************************
         * Constructor
         */
        public Result() {
        }

        public Result(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public static Result success() {
            Result result = new Result();
            result.setCode(ResponseCode.SUCCESS);
            return result;
        }

        public static Result success(String msg) {
            Result result = new Result();
            result.setCode(ResponseCode.SUCCESS);
            result.setMsg(msg);
            return result;
        }

        public static Result success(String msg, Object data) {
            Result result = new Result();
            result.setCode(ResponseCode.SUCCESS);
            result.setMsg(msg);
            result.setData(data);
            return result;
        }

        public static Result failure(String code, String msg) {
            Result result = new Result();
            result.setCode(code);
            result.setMsg(msg);
            return result;
        }

        public static Result failure(String code, String msg, Object data) {
            Result result = new Result();
            result.setCode(code);
            result.setMsg(msg);
            result.setData(data);
            return result;
        }
    }
