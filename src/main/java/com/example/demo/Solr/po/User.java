package com.example.demo.Solr.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

/**
 * @program: demo
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-15 17:05
 **/
@TableName("solr_user")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 5795039982891120283L;
    //必须实现可序列化接口，要在网络上传输
        @Field("id")
        private String id;
        @Field("item_name")
        private String name;
        @Field("item_sex")
        private String sex;
        @Field("item_address")
        private String address;
        @Field("item_host")
        private Integer host;
}
