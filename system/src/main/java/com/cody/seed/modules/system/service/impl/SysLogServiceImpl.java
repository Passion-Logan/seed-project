package com.cody.seed.modules.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cody.common.entity.SysBaseModel;
import com.cody.common.system.api.ISysBaseAPI;
import com.cody.common.system.vo.BasicPageVo;
import com.cody.seed.modules.system.entity.SysLog;
import com.cody.seed.modules.system.mapper.SysLogMapper;
import com.cody.seed.modules.system.service.ISysLogService;
import com.cody.seed.modules.vo.request.SysLogQueryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Administrator
 * @date 2021/9/13
 * @lastUpdateUser Administrator
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/13
 */
@Service
@Slf4j
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

    @Resource
    private SysLogMapper sysLogMapper;
    @Resource
    private ISysBaseAPI sysBaseApi;

    /**
     * 清空所有日志记录
     */
    @Override
    public void removeAll() {
        sysLogMapper.removeAll();
    }

    @Override
    public Long findTotalVisitCount() {
        return sysLogMapper.findTotalVisitCount();
    }

    @Override
    public Long findTodayVisitCount(Date dayStart, Date dayEnd) {
        return sysLogMapper.findTodayVisitCount(dayStart, dayEnd);
    }

    @Override
    public Long findTodayIp(Date dayStart, Date dayEnd) {
        return sysLogMapper.findTodayIp(dayStart, dayEnd);
    }

    @Override
    public List<Map<String, Object>> findVisitCount(Date dayStart, Date dayEnd) {
        try {
            String dbType = sysBaseApi.getDatabaseType();
            return sysLogMapper.findVisitCount(dayStart, dayEnd, dbType);
        } catch (SQLException e) {
            log.error("错误信息:{}", e.getMessage());
        }
        return null;
    }

    @Override
    public BasicPageVo<SysLog> getByPage(SysLogQueryVO vo) {
        Page<SysLog> page = new Page<>(vo.getCurrent(), vo.getPageSize());
        System.out.println(Objects.nonNull(vo.getCreateTime()));
        IPage<SysLog> data = this.page(page, Wrappers.<SysLog>lambdaQuery()
                .like(StrUtil.isNotBlank(vo.getLogContent()), SysLog::getLogContent, vo.getLogContent())
                .like(StrUtil.isNotBlank(vo.getUsername()), SysLog::getUsername, vo.getUsername())
                .eq(StrUtil.isNotBlank(vo.getOperateType()), SysLog::getOperateType, vo.getOperateType())
                .between(Objects.nonNull(vo.getCreateTime()),
                        SysLog::getCreateTime, Objects.nonNull(vo.getCreateTime()) ? vo.getCreateTime()[0] + " 00:00:00" : "",
                        Objects.nonNull(vo.getCreateTime()) ? vo.getCreateTime()[1] + " 23:59:59" : "")
                .orderByDesc(SysBaseModel::getCreateTime)
        );
        return BasicPageVo.ofPages(data);
    }

}
