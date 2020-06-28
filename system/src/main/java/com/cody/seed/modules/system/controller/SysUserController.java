package com.cody.seed.modules.system.controller;

import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: SysUserController
 *
 * @author WQL
 * @Description:
 * @date: 2020/6/28 23:22
 * @since JDK 1.8
 */
@RestController
@Api(value = "SysUserController",tags = "系统-用户管理")
@RequestMapping("/sys/user")
@Validated
public class SysUserController {
}
