package com.example.demo.StrategyPattern.Enum;

public enum MSG_TYPE {

    TEXT(1, "文本"),
    IMAGE(2, "图片"),
    VIDEO(3, "视频");

    public final int code;
    public final String name;

    MSG_TYPE(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
