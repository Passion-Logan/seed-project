package com.cody.seed.modules.system.controller;

import com.cody.seed.modules.system.entity.SysUser;
import com.cody.seed.modules.system.service.ISysUserRoleService;
import com.cody.seed.modules.system.service.ISysUserService;
import com.cody.seed.modules.util.BeanUtil;
import com.cody.seed.modules.vo.request.SysUserQueryVO;
import com.cody.seed.modules.vo.response.SysUserPageInfoVO;
import com.cody.seed.modules.vo.response.SysUserResponseVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * ClassName: SysUserController
 *
 * @author WQL
 * @Description:
 * @date: 2020/6/28 23:22
 * @since JDK 1.8
 */
@RestController
@Api(value = "SysUserController", tags = "系统-用户管理")
@RequestMapping("/sys/user")
@Validated
@Slf4j
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    /**
     * 分页查询
     *
     * @param sysUserQueryVO 分页查询参数
     * @return 分页参数
     */
    @ApiOperation(value = "分页查询")
    @PostMapping("getPageList")
    public SysUserPageInfoVO selectPageList(@RequestBody @Valid SysUserQueryVO sysUserQueryVO) {
        String info = String.format("The method name[selectPageList] params:%s", sysUserQueryVO.toString());
        log.info(info);
        SysUser sysUser = BeanUtil.convert(sysUserQueryVO, SysUser.class);

        Page<SysUserResponseVO> page = PageHelper.startPage(sysUserQueryVO.getCurrent(), sysUserQueryVO.getPageSize());
        List<SysUser> sysUserList = sysUserService.getList(sysUser);
        SysUserPageInfoVO sysUserPageInfo = new SysUserPageInfoVO();
        BeanUtils.copyProperties(page.toPageInfo(), sysUserPageInfo);
        List<SysUserResponseVO> voList = BeanUtil.convert(sysUserList, SysUserResponseVO.class);
        sysUserPageInfo.setList(voList);

        return sysUserPageInfo;
    }
}
