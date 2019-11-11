package com.example.demo.FastDfs.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @program: 图片上传配置类
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-09 11:41
 **/
@Component
@PropertySource("classpath:conf/pic_upload.properties")
@Data
public class PicUploadConf {

    /**
     * 商品缩略图1-宽度
     */
    @Value("${goods.thumb-image-1.width}")
    private int goodsThumbImage1Width;

    /**
     * 商品缩略图1-宽度
     */
    @Value("${goods.thumb-image-1.height}")
    private int goodsThumbImage1Height;

    /**
     * 商品缩略图2-宽度
     */
    @Value("${goods.thumb-image-2.width}")
    private int goodsThumbImage2Width;

    /**
     * 商品缩略图2-宽度
     */
    @Value("${goods.thumb-image-2.height}")
    private int goodsThumbImage2Height;


}
