package com.example.demo.FastDfs.conf;

import com.github.tobato.fastdfs.FdfsClientConfig;
import com.github.tobato.fastdfs.domain.MataData;
import com.github.tobato.fastdfs.domain.StorageNode;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.exception.FdfsUnsupportImageTypeException;
import com.github.tobato.fastdfs.exception.FdfsUploadImageException;
import com.github.tobato.fastdfs.proto.storage.StorageSetMetadataCommand;
import com.github.tobato.fastdfs.proto.storage.StorageUploadFileCommand;
import com.github.tobato.fastdfs.proto.storage.StorageUploadSlaveFileCommand;
import com.github.tobato.fastdfs.proto.storage.enums.StorageMetdataSetType;
import com.github.tobato.fastdfs.service.DefaultGenerateStorageClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @program: demo
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-09 11:43
 **/
@Component
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class GoodsPicFastFileStorageClient extends DefaultGenerateStorageClient implements FastFileStorageClient {

    @Resource
    private PicUploadConf picUploadConf;

    /** 支持的图片类型 */
    private static final String[] SUPPORT_IMAGE_TYPE = { "JPG", "JPEG", "PNG", "GIF", "BMP", "WBMP" };
    private static final List<String> SUPPORT_IMAGE_LIST = Arrays.asList(SUPPORT_IMAGE_TYPE);

    /** 缩略图生成配置 */
    @Resource
    private ThumbImageConfig thumbImageConfig;

    @Override
    public StorePath uploadFile(InputStream inputStream, long fileSize, String fileExtName, Set<MataData> metaDataSet) {
        Validate.notNull(inputStream, "上传文件流不能为空");
        Validate.notBlank(fileExtName, "文件扩展名不能为空");
        StorageNode client = trackerClient.getStoreStorage();
        return uploadFileAndMataData(client, inputStream, fileSize, fileExtName, metaDataSet);
    }

    @Override
    public StorePath uploadImageAndCrtThumbImage(InputStream inputStream, long fileSize, String fileExtName, Set<MataData> metaDataSet) {
        Validate.notNull(inputStream, "上传文件流不能为空");
        Validate.notBlank(fileExtName, "文件扩展名不能为空");

        // 检查是否能处理此类图片
        if (!isSupportImage(fileExtName)) {
            throw new FdfsUnsupportImageTypeException("不支持的图片格式" + fileExtName);
        }

        StorageNode client = trackerClient.getStoreStorage();
        byte[] bytes = inputStreamToByte(inputStream);

        // 上传文件和mataData
        StorePath path = uploadFileAndMataData(client, new ByteArrayInputStream(bytes), fileSize, fileExtName, metaDataSet);

        // 上传缩略图
        uploadThumbImage(client, new ByteArrayInputStream(bytes), path.getPath(), fileExtName,
                picUploadConf.getGoodsThumbImage1Width(), picUploadConf.getGoodsThumbImage1Height());
        uploadThumbImage(client, new ByteArrayInputStream(bytes), path.getPath(), fileExtName,
                picUploadConf.getGoodsThumbImage2Width(), picUploadConf.getGoodsThumbImage2Height());

        bytes = null;
        return path;
    }

    @Override
    public void deleteFile(String filePath) {
        StorePath storePath = StorePath.praseFromUrl(filePath);
        super.deleteFile(storePath.getGroup(), storePath.getPath());
    }

    /**
     * 上传文件和元数据
     *
     * @param client
     * @param inputStream
     * @param fileSize
     * @param fileExtName
     * @param metaDataSet
     * @return
     */
    private StorePath uploadFileAndMataData(StorageNode client, InputStream inputStream, long fileSize,
                                            String fileExtName, Set<MataData> metaDataSet) {
        // 上传文件
        StorageUploadFileCommand command = new StorageUploadFileCommand(client.getStoreIndex(), inputStream,
                fileExtName, fileSize, false);
        StorePath path = connectionManager.executeFdfsCmd(client.getInetSocketAddress(), command);
        // 上传matadata
        if (hasMataData(metaDataSet)) {
            StorageSetMetadataCommand setMDCommand = new StorageSetMetadataCommand(path.getGroup(), path.getPath(),
                    metaDataSet, StorageMetdataSetType.STORAGE_SET_METADATA_FLAG_OVERWRITE);
            connectionManager.executeFdfsCmd(client.getInetSocketAddress(), setMDCommand);
        }
        return path;
    }

    /**
     * 检查是否有MataData
     *
     * @param metaDataSet
     * @return
     */
    private boolean hasMataData(Set<MataData> metaDataSet) {
        return null != metaDataSet && !metaDataSet.isEmpty();
    }

    /**
     * 是否是支持的图片文件
     *
     * @param fileExtName
     * @return
     */
    private boolean isSupportImage(String fileExtName) {
        return SUPPORT_IMAGE_LIST.contains(fileExtName.toUpperCase());
    }

    /**
     * 获取byte流
     *
     * @param inputStream
     * @return
     */
    private byte[] inputStreamToByte(InputStream inputStream) {
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            LOGGER.error("image inputStream to byte error", e);
            throw new FdfsUploadImageException("upload ThumbImage error", e.getCause());
        }
    }

    /**
     * 上传缩略图
     *
     * @param client
     * @param inputStream
     * @param masterFilename
     * @param fileExtName
     */
    private void uploadThumbImage(StorageNode client, InputStream inputStream, String masterFilename, String fileExtName, int width, int height) {
        ByteArrayInputStream thumbImageStream = null;
        try {
            thumbImageStream = getThumbImageStream(inputStream, width, height);
            // 获取文件大小
            long fileSize = thumbImageStream.available();
            // 获取缩略图前缀
            String prefixName = thumbImageConfig.getPrefixName();
            prefixName = new StringBuffer("_").append(width).append("x").append(height).toString();
            StorageUploadSlaveFileCommand command = new StorageUploadSlaveFileCommand(thumbImageStream, fileSize,
                    masterFilename, prefixName, fileExtName);
            connectionManager.executeFdfsCmd(client.getInetSocketAddress(), command);
        } catch (IOException e) {
            LOGGER.error("upload ThumbImage error", e);
            throw new FdfsUploadImageException("upload ThumbImage error", e.getCause());
        } finally {
            IOUtils.closeQuietly(thumbImageStream);
        }
    }

    /**
     * 获取缩略图
     * @param inputStream
     * @param width 宽度
     * @param height 高度
     * @return ByteArrayInputStream
     * @throws IOException
     */
    private ByteArrayInputStream getThumbImageStream(InputStream inputStream, int width, int height) throws IOException {
        // 在内存当中生成缩略图
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //@formatter:off
        Thumbnails
                .of(inputStream)
                .size(width, height)
                .toOutputStream(out);
        //@formatter:on
        return new ByteArrayInputStream(out.toByteArray());
    }

}