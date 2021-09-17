package com.cody.seed.modules.system.service.impl;

import com.cody.common.constant.CommonConstant;
import com.cody.common.constant.DataBaseConstant;
import com.cody.common.system.api.ISysBaseAPI;
import com.cody.common.util.SpringContextUtils;
import com.cody.common.util.oConvertUtils;
import com.cody.common.util.oss.OssBootUtil;
import com.cody.seed.modules.system.execption.CustomExecption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * @author Administrator
 * @Description: TODO
 * @date: 2020年06月17日 14:13
 */
@Slf4j
@Service
public class SysBaseImpl implements ISysBaseAPI {

    /**
     * 当前系统数据库类型
     */
    private static String DB_TYPE = "";

    @Override
    public String getDatabaseType() throws SQLException {
        if (oConvertUtils.isNotEmpty(DB_TYPE)) {
            return DB_TYPE;
        }
        DataSource dataSource = SpringContextUtils.getApplicationContext().getBean(DataSource.class);
        return getDatabaseTypeByDataSource(dataSource);
    }

    @Override
    public String upload(MultipartFile file, String bizPath, String uploadType) {
        String url = "";
        if (CommonConstant.UPLOAD_TYPE_MINIO.equals(uploadType)) {
            //url = MinioUtil.upload(file,bizPath);
        } else {
            url = OssBootUtil.upload(file, bizPath);
        }
        return url;
    }

    @Override
    public String upload(MultipartFile file, String bizPath, String uploadType, String customBucket) {
        return null;
    }

    /**
     * 获取数据库类型
     *
     * @param dataSource
     * @return
     * @throws SQLException
     */
    private String getDatabaseTypeByDataSource(DataSource dataSource) throws SQLException {
        if ("".equals(DB_TYPE)) {
            Connection connection = dataSource.getConnection();
            try {
                DatabaseMetaData md = connection.getMetaData();
                String dbType = md.getDatabaseProductName().toLowerCase();
                if (dbType.indexOf("mysql") >= 0) {
                    DB_TYPE = DataBaseConstant.DB_TYPE_MYSQL;
                } else if (dbType.indexOf("oracle") >= 0) {
                    DB_TYPE = DataBaseConstant.DB_TYPE_ORACLE;
                } else if (dbType.indexOf("sqlserver") >= 0 || dbType.indexOf("sql server") >= 0) {
                    DB_TYPE = DataBaseConstant.DB_TYPE_SQLSERVER;
                } else if (dbType.indexOf("postgresql") >= 0) {
                    DB_TYPE = DataBaseConstant.DB_TYPE_POSTGRESQL;
                } else {
                    throw new CustomExecption("数据库类型:[" + dbType + "]不识别!");
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
                connection.close();
            }
        }
        return DB_TYPE;

    }
}
