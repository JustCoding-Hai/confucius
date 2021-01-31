package top.javahai.confucius.service.oss.service;

import java.io.InputStream;

/**
 * @author Hai
 * @program: zhkuschool-frame
 * @description:
 * @create 2021/1/11 - 0:00
 **/
public interface FileService {

    /**
     * 上传文件
     * @param inputStream 文件的输入流
     * @param module  保存的目录
     * @param originalFileName
     * @return
     */
    String upload(InputStream inputStream, String module, String originalFileName);

    /**
     * 根据url删除阿里云存储的文件
     * @param url
     */
    void removeFile(String url);
}
