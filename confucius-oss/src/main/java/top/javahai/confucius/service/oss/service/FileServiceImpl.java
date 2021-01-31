package top.javahai.confucius.service.oss.service;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.javahai.confucius.service.oss.constant.OssProperties;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author Hai
 * @program: zhkuschool-frame
 * @description:
 * @create 2021/1/11 - 0:08
 **/
@Service("fileService")
public class FileServiceImpl implements FileService {

    private static final Logger LOGGER= LoggerFactory.getLogger(FileServiceImpl.class);
    @Autowired
    private OssProperties ossProperties;

    @Override
    public String upload(InputStream inputStream, String module, String originalFileName) {

        String endpoint = ossProperties.getEndpoint();
        String bucketName = ossProperties.getBucketName();
        String accessKeyId = ossProperties.getAccessKeyId();
        String accessKeySecret = ossProperties.getSecretAccessKey();

        //判断Bucket是否存在，如果不存在就创建
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        if(!ossClient.doesBucketExist(bucketName)){
            ossClient.createBucket(bucketName);
            //设置oss实例的访问权限为：公共读
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        }

        //构建文件保存目录
        String folder=new DateTime().toString("yyyy/MM/dd");

        //构建文件名
        String fileName = UUID.randomUUID().toString();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".")+1);
        String key = new StringBuffer().append(module)
                .append("/")
                .append(folder)
                .append("/")
                .append(fileName)
                .append(".")
                .append(fileExtension).toString();

        //String key=module+"/"+folder+"/"+fileName+"."+

        //文件上传到阿里云
        ossClient.putObject(ossProperties.getBucketName(),key,inputStream);

        //关闭阿里云连接
        ossClient.shutdown();
        //文件上传成功后文件的url地址
        String url="https://"+bucketName+"."+endpoint+"/"+key;

        LOGGER.info("FileServiceImpl image upload successfully :[{}]",url);
        return url;
    }

    @Override
    public void removeFile(String url) {
        String endpoint = ossProperties.getEndpoint();
        String bucketName = ossProperties.getBucketName();
        String accessKeyId = ossProperties.getAccessKeyId();
        String accessKeySecret = ossProperties.getSecretAccessKey();

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //文件url：https://zhku-school-bucket01.oss-cn-beijing.aliyuncs.com/avatar/2021/01/11/dcb11e0a-1c86-444f-b8b0-f51442a3e49a.jpg
        //截取文件名
        String host="https://"+bucketName+"."+endpoint+"/";
        String fileName=url.substring(host.length());

        LOGGER.info("File [{}] is going to be deleted",fileName);
        //删除文件
        ossClient.deleteObject(bucketName,fileName);
        ossClient.shutdown();
    }
}
