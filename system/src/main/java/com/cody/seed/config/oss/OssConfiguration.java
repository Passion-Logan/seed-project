package com.cody.seed.config.oss;

import com.cody.common.util.oss.OssBootUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Oss设置
 *
 * @author wql
 * @desc OssConfiguration
 * @date 2021/9/24
 * @lastUpdateUser wql
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/24
 */
@Configuration
public class OssConfiguration {
    @Value("${seed.oss.endpoint}")
    private String endpoint;
    @Value("${seed.oss.accessKey}")
    private String accessKeyId;
    @Value("${seed.oss.secretKey}")
    private String accessKeySecret;
    @Value("${seed.oss.bucketName}")
    private String bucketName;
    @Value("${seed.oss.staticDomain}")
    private String staticDomain;


    @Bean
    public void initOssBootConfiguration() {
        OssBootUtil.setEndPoint(endpoint);
        OssBootUtil.setAccessKeyId(accessKeyId);
        OssBootUtil.setAccessKeySecret(accessKeySecret);
        OssBootUtil.setBucketName(bucketName);
        OssBootUtil.setStaticDomain(staticDomain);
    }
}
