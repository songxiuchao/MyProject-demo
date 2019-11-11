package com.example.demo.Mongodb.Enum;

public enum OrderEnum {
    Fail(0,"Fail","下单失败"),
    Success(1,"Success","下单成功");

    private int index;
    private String name;
    private String desc;

    OrderEnum(int index, String name, String desc) {
        this.index = index;
        this.name = name;
        this.desc = desc;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
