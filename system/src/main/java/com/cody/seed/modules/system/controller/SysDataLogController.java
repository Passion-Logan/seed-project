package com.cody.seed.modules.system.controller;

import com.cody.common.system.vo.BasicPageVo;
import com.cody.seed.modules.system.entity.SysLog;
import com.cody.seed.modules.system.service.ISysLogService;
import com.cody.seed.modules.vo.request.SysLogQueryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Administrator
 * @desc SysDataLogController
 * @date 2021/9/14
 * @lastUpdateUser Administrator
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/14
 */
@RestController
@Api(value = "SysDataLogController", tags = "系统-日志管理")
@RequestMapping("/sys/log")
@Validated
@Slf4j
public class SysDataLogController {

    @Resource
    private ISysLogService sysLogService;

    @ApiOperation(value = "分页查询")
    @PostMapping("getPageList")
    public BasicPageVo<SysLog> selectPageList(@RequestBody @Valid SysLogQueryVO vo) {
        return sysLogService.getByPage(vo);
    }

}
