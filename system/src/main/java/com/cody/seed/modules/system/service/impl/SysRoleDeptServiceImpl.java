package com.cody.seed.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cody.seed.modules.system.entity.SysRoleDept;
import com.cody.seed.modules.system.mapper.SysRoleDeptMapper;
import com.cody.seed.modules.system.service.ISysRoleDeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptMapper, SysRoleDept> implements ISysRoleDeptService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleDeptServiceImpl.class);

}
