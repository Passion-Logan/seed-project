package com.cody.seed.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cody.seed.modules.system.entity.SysLoginLog;
import com.cody.seed.modules.system.mapper.SysLoginLogMapper;
import com.cody.seed.modules.system.service.ISysLoginLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements ISysLoginLogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysLoginLogServiceImpl.class);

}
