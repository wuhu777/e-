package com.atguigu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.oss.service.OssService;

import com.atguigu.oss.utils.ConstandPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // 工具类获取值
        String endpoint = ConstandPropertiesUtils.END_POINT;
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstandPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstandPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketname=ConstandPropertiesUtils.BUCKET_NAME;
// 创建OSSClient实例。
        try {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 上传文件流。
        InputStream inputStream = file.getInputStream();
        //获取文件名称
        String fileName=file.getOriginalFilename();
        //在文件名称里面添加随机唯一的值
        String uuid= UUID.randomUUID().toString().replaceAll("-","");
        fileName=uuid+fileName;
            String datePath = new DateTime().toString("yyyy/MM/dd");
            fileName=datePath+"/"+fileName;
            //第一个参数，Bucket名称
            //第二个参数，上传到oss文件路径和文件名称
            //第三个参数，上传文件输入流
            ossClient.putObject(bucketname, fileName, inputStream);
        // 关闭OSSClient。
            ossClient.shutdown();

            //把上传之后的文件路径返回
            //需要把上传的阿里云oss拼接出来
            //https://edu-922.oss-cn-shanghai.aliyuncs.com/1-3.jpg
            String url="https://"+bucketname+"."+endpoint+"/"+fileName;
            return url;
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }
}
