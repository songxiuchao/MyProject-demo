package com.example.demo.FastDfs.controller;

import com.example.demo.Common.constant.ResponseCode;
import com.example.demo.Common.constant.Result;
import com.example.demo.Common.entity.FileUploadResult;
import com.example.demo.Common.entity.PicFileUploadResult;
import com.example.demo.FastDfs.conf.GoodsPicFastFileStorageClient;
import com.example.demo.FastDfs.conf.PicUploadConf;
import com.github.tobato.fastdfs.domain.StorePath;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: demo
 * @description: 测试类
 * @author: xiuchao Song
 * @create: 2019-11-09 11:42
 **/
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Resource
    private PicUploadConf picUploadConf;

    @Resource
    private GoodsPicFastFileStorageClient goodsPicFastFileStorageClient;

    /**
     * 上传文件
     */
    @PostMapping("/file")
    public Result uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file == null) {
            return Result.failure(ResponseCode.ERROR_FILE_UPLOAD, "上传文件为空!", null);
        }

        // 获得文件扩展名
        String extendName = FilenameUtils.getExtension(file.getOriginalFilename());

        // 执行上传操作
        StorePath storePath = goodsPicFastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), extendName, null);
        String path = "/" + storePath.getFullPath();

        // 封装上传后的对象并返回
        FileUploadResult uploadResult = new FileUploadResult("", extendName, file.getSize(), path);
        return Result.success("上传成功!", uploadResult);
    }

    /**
     * 上传商品图片（返回原图 + 2个自动生成的缩略图）
     */
    @PostMapping("/goodsPic")
    public Result singleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file == null) {
            return Result.failure(ResponseCode.ERROR_FILE_UPLOAD, "上传文件为空!", null);
        }

        // 获得文件扩展名
        String extendName = FilenameUtils.getExtension(file.getOriginalFilename());

        // 执行上传操作（上传原图 + 生成2个缩略图）
        StorePath storePath = goodsPicFastFileStorageClient.uploadImageAndCrtThumbImage(file.getInputStream(),
                file.getSize(), extendName, null);
        String path = "/" + storePath.getFullPath();

        // 获取缩略图路径
        StringBuilder sbuFileSize1 = new StringBuilder();
        sbuFileSize1.append(picUploadConf.getGoodsThumbImage1Width()).append("x").append(picUploadConf.getGoodsThumbImage1Height());
        StringBuilder sbuFileSize2 = new StringBuilder();
        sbuFileSize2.append(picUploadConf.getGoodsThumbImage2Width()).append("x").append(picUploadConf.getGoodsThumbImage2Height());
        String compressPath1 = getThumbImagePath(storePath.getPath(), picUploadConf.getGoodsThumbImage1Width(), picUploadConf.getGoodsThumbImage1Height());
        String compressPath2 = getThumbImagePath(storePath.getPath(), picUploadConf.getGoodsThumbImage2Width(), picUploadConf.getGoodsThumbImage2Height());

        // 封装上传后的对象并返回
        List<PicFileUploadResult> lstPicFileUploadResult = new ArrayList<PicFileUploadResult>();
        PicFileUploadResult result = new PicFileUploadResult();
        result.setPath(path);
        result.setExtendName(extendName);
        result.setSize(file.getSize());
        result.setOrigin(Boolean.TRUE);
        result.setModel("L");
        lstPicFileUploadResult.add(result);
        // 缩略图1
        PicFileUploadResult result1 = new PicFileUploadResult();
        result1.setPath("/" + storePath.getGroup() + "/" + compressPath1);
        result1.setExtendName(extendName);
        result1.setOrigin(Boolean.FALSE);
        result1.setModel("M");
        result1.setWidth(picUploadConf.getGoodsThumbImage1Width());
        result1.setHeight(picUploadConf.getGoodsThumbImage1Height());
        lstPicFileUploadResult.add(result1);
        // 缩略图2
        PicFileUploadResult result2 = new PicFileUploadResult();
        result2.setPath("/" + storePath.getGroup() + "/" + compressPath2);
        result2.setExtendName(extendName);
        result2.setOrigin(Boolean.FALSE);
        result2.setModel("S");
        result2.setWidth(picUploadConf.getGoodsThumbImage2Width());
        result2.setHeight(picUploadConf.getGoodsThumbImage2Height());
        lstPicFileUploadResult.add(result2);

        return Result.success("上传成功!", lstPicFileUploadResult);
    }

    /**
     * 主文件名称
     * @param masterFilename
     * @param width
     * @param height
     * @return
     */
    private String getThumbImagePath(String masterFilename, int width, int height) {
        Validate.notBlank(masterFilename, "主文件不能为空");
        StringBuilder buff = new StringBuilder(masterFilename);
        int index = buff.lastIndexOf(".");

        StringBuilder buffer = new StringBuilder();
        buffer.append("_").append(width).append("x").append(height);
        String filename = buffer.toString();
        buff.insert(index, filename);
        return buff.toString();
    }


}
