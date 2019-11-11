package com.example.demo.Common.entity;

/**
 * @program: 图片文件上传结果实体类
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-09 11:49
 **/
public class PicFileUploadResult extends FileUploadResult {

    private static final long serialVersionUID = 1657291949574411841L;

    /**
     * 宽度
     */
    private int width;

    /**
     * 高度
     */
    private int height;

    /**
     * 图片型号：大，中，小
     */
    private String model;

    /**
     * 是否源文件
     */
    private boolean origin;


    public boolean getOrigin() {
        return origin;
    }

    public void setOrigin(boolean origin) {
        this.origin = origin;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}