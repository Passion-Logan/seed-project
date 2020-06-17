package com.cody.restful.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cody.restful.entity.Test;
import com.cody.restful.mapper.TestMapper;
import com.cody.restful.service.ITestService;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @date: 2020年06月17日 16:30
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {
}
