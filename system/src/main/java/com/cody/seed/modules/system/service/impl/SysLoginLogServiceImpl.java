package com.cody.seed.modules.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cody.common.system.vo.BasicPageVo;
import com.cody.seed.modules.system.entity.SysLoginLog;
import com.cody.seed.modules.system.mapper.SysLoginLogMapper;
import com.cody.seed.modules.system.service.ISysLoginLogService;
import com.cody.seed.modules.vo.request.SysLoginLogQueryVO;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Administrator
 * @date 2021/9/13
 * @lastUpdateUser Administrator
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/13
 */
@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements ISysLoginLogService {

    @Override
    public BasicPageVo<SysLoginLog> getByPage(SysLoginLogQueryVO vo) {

        return BasicPageVo.ofPages(this.page(new Page<>(vo.getCurrent(), vo.getPageSize()),
                Wrappers.<SysLoginLog>lambdaQuery()
                        .like(StrUtil.isNotBlank(vo.getLoginName()), SysLoginLog::getLoginName, vo.getLoginName())
                        .between(Objects.nonNull(vo.getLoginTime()), SysLoginLog::getLoginTime,
                                Objects.nonNull(vo.getLoginTime()) ? vo.getLoginTime()[0] + " 00:00:00" : "",
                                Objects.nonNull(vo.getLoginTime()) ? vo.getLoginTime()[1] + " 23:59:59" : "")
                        .orderByDesc(SysLoginLog::getLoginTime)
                )
        );

    }
}
