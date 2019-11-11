package com.example.demo.Common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: 文件上传结果实体类
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-09 11:48
 **/
public class FileUploadResult implements Serializable {

    private static final long serialVersionUID = -8091517165014047219L;

    /**
     * 文件名称（用户自定义，用于显示）
     */
    private String name;

    /**
     * 文件扩展名（通过上传的源文件类型获得）
     */
    private String extendName;

    /**
     * 文件大小（单位：字节）
     */
    private long size;

    /**
     * 上传后的文件存储路径
     */
    private String path;

    /**
     * 文件上传时间
     */
    private Date time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtendName() {
        return extendName;
    }

    public void setExtendName(String extendName) {
        this.extendName = extendName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Constructor
     */
    public FileUploadResult() {
        this.time = new Date();
    }

    /**
     * Constructor
     * @param name 文件名称（用户自定义，显示用）
     * @param extendName 文件扩展名
     * @param size 文件大小
     * @param path 上传后的文件路径
     */
    public FileUploadResult(String name, String extendName, long size, String path) {
        this.name = name;
        this.extendName = extendName;
        this.size = size;
        this.path = path;
        this.time = new Date();
    }

}
