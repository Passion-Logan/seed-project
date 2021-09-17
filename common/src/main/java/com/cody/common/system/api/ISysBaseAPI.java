package com.cody.common.system.api;

import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;

/**
 * 底层共通业务API，提供其他独立模块调用
 *
 * @author Administrator
 */
public interface ISysBaseAPI {

    /**
     * 获取当前数据库类型
     *
     * @return
     * @throws SQLException
     */
    String getDatabaseType() throws SQLException;

    /**
     * 文件上传
     *
     * @param file       文件
     * @param bizPath    自定义路径
     * @param uploadType 上传方式
     * @return
     */
    String upload(MultipartFile file, String bizPath, String uploadType);

    /**
     * 文件上传 自定义桶
     *
     * @param file         file
     * @param bizPath      bizPath
     * @param uploadType   uploadType
     * @param customBucket customBucket
     * @return
     */
    String upload(MultipartFile file, String bizPath, String uploadType, String customBucket);

}
