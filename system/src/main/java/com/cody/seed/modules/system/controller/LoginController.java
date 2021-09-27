package com.cody.seed.modules.system.controller;

import cn.hutool.core.util.StrUtil;
import com.cody.common.api.vo.Result;
import com.cody.common.aspect.annotation.NoRepeatSubmit;
import com.cody.common.constant.CommonConstant;
import com.cody.common.system.api.ISysBaseAPI;
import com.cody.common.util.IPUtilsPro;
import com.cody.common.util.oConvertUtils;
import com.cody.seed.modules.security.JwtAuthenticatioToken;
import com.cody.seed.modules.system.entity.SysLoginLog;
import com.cody.seed.modules.system.entity.SysMenu;
import com.cody.seed.modules.system.entity.SysRole;
import com.cody.seed.modules.system.entity.SysUser;
import com.cody.seed.modules.system.execption.CustomExecption;
import com.cody.seed.modules.system.service.ISysLoginLogService;
import com.cody.seed.modules.system.service.ISysMenuService;
import com.cody.seed.modules.system.service.ISysRoleService;
import com.cody.seed.modules.system.service.ISysUserService;
import com.cody.seed.modules.util.BeanUtil;
import com.cody.seed.modules.util.SecurityUtils;
import com.cody.seed.modules.vo.LoginRequestVO;
import com.cody.seed.modules.vo.request.SysUserPwdVO;
import com.cody.seed.modules.vo.response.MenuResponseVO;
import com.cody.seed.modules.vo.response.SysRoleResponseVO;
import com.cody.seed.modules.vo.response.SysUserInfoResponseVO;
import com.cody.seed.modules.vo.response.UserInfoResponseVO;
import com.wf.captcha.ArithmeticCaptcha;
import com.zengtengpeng.operation.RedissonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * 除了使用登录认证过滤器拦截 /login Post请求之外，我们也可以不使用上面的过滤器，通过自定义登录接口实现，
 * 只要在登录接口手动触发登录流程并生产令牌即可。
 * <p>
 * 其实 Spring Security 的登录认证过程只需调用 AuthenticationManager 的 authenticate(Authentication authentication) 方法，
 * 最终返回认证成功的 Authentication 实现类并存储到SpringContexHolder 上下文即可，这样后面授权的时候就可以从
 * SpringContexHolder 中获取登录认证信息，并根据其中的用户信息和权限信息决定是否进行授权。
 * @Description: 登录控制器
 * @date: 2020年06月17日 16:39
 */
@Api(value = "LoginController", tags = "系统-spring security登录")
@RequestMapping("/auth")
@RestController
@Slf4j
public class LoginController {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private ISysUserService sysUserService;
    @Resource
    private ISysRoleService roleService;
    @Resource
    private ISysMenuService sysMenuService;
    @Resource
    private ISysLoginLogService loginLogService;
    @Resource
    private RedissonObject redissonObject;

    /**
     * 本地：local minio：minio 阿里：alioss
     */
    @Value(value = "${seed.uploadType}")
    private String uploadType;

    @Value(value = "${seed.path.upload}")
    private String uploadPath;

    @Resource
    private ISysBaseAPI sysBaseAPI;

    /**
     * 登录接口
     * 注意：如果使用此登录控制器触发登录认证，需要禁用登录认证过滤器，即将 WebSecurityConfig 中的以下配置项注释即可，
     * http.addFilterBefore(new JwtLoginFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
     * 否则访问登录接口会被过滤拦截，
     * 执行不会再进入此登录接口，大家根据使用习惯二选一即可
     */
    @NoRepeatSubmit
    @PostMapping(value = "/login")
    public JwtAuthenticatioToken login(@RequestBody LoginRequestVO loginRequestVO, HttpServletRequest request) {
        // 查询验证码
        String code = redissonObject.getValue(loginRequestVO.getUuid());
        // 清除验证码
        redissonObject.delete(loginRequestVO.getUuid());
        if (StringUtils.isBlank(code)) {
            log.error("验证码不存在或已过期");
            throw new CustomExecption("验证码不存在或已过期");
        }
        if (StringUtils.isBlank(loginRequestVO.getImgCode()) || !loginRequestVO.getImgCode().equalsIgnoreCase(code)) {
            log.error("验证码错误");
            throw new CustomExecption("验证码错误");
        }
        String username = loginRequestVO.getUsername();
        String password = loginRequestVO.getPassword();

        // 系统登录认证
        JwtAuthenticatioToken token = SecurityUtils.login(request, username, password, authenticationManager);

        //添加登录日志
        try {
            insertLoginLog(loginRequestVO, request);
        } catch (Exception e) {
            log.error("添加登录日志错误:{}", e.getMessage());
        }

        return token;
    }

    /**
     * 记录登录日志
     *
     * @param loginRequestVO SysUserInfoResponseVO
     * @param request        SysUserInfoResponseVO
     */
    private void insertLoginLog(LoginRequestVO loginRequestVO, HttpServletRequest request) {
        //记录登录日志
        SysLoginLog loginLog = new SysLoginLog();
        loginLog.setLoginName(loginRequestVO.getUsername());
        String ip = IPUtilsPro.getIpAddr(request);
        loginLog.setIp(ip);
        loginLog.setLoginLocation(IPUtilsPro.getCityInfo(ip));
        loginLog.setBrowser(IPUtilsPro.getBrowser(request));
        loginLog.setOs(IPUtilsPro.getOperatingSystem(request));
        loginLog.setStatus(0);
        loginLog.setLoginTime(new Date());

        loginLogService.save(loginLog);
    }

    /**
     * 验证码
     *
     * @return Result
     */
    @NoRepeatSubmit
    @ApiOperation("获取验证码")
    @GetMapping(value = "/captcha")
    public Result<Map<String, Object>> getCode() {
        // 算术类型 https://gitee.com/whvse/EasyCaptcha
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36);
        // 几位数运算，默认是两位
        captcha.setLen(2);
        // 获取运算的结果
        String result = captcha.text();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        // 保存到redis
        redissonObject.setValue(uuid, result, 60 * 5 * 1000L);
        // 验证码信息
        Map<String, Object> imgResult = new HashMap<String, Object>(2) {{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};
        return Result.ok(imgResult);
    }

    /**
     * 登录用户信息
     *
     * @return SysUserInfoResponseVO
     */
    @ApiOperation("登录用户信息")
    @GetMapping(value = "/user/info")
    public SysUserInfoResponseVO getUserInfo() {
        //查询用户信息
        SysUser userDTO = new SysUser();
        userDTO.setUserName(SecurityUtils.getUsername());
        SysUser user = sysUserService.findByUsername(SecurityUtils.getUsername(), null);
        SysUserInfoResponseVO responseVo = BeanUtil.convert(user, SysUserInfoResponseVO.class);

        //查询角色信息
        List<SysRole> roles = roleService.getRolesByUserId(user.getId());
        List<SysRoleResponseVO> roleList = BeanUtil.convert(roles, SysRoleResponseVO.class);
        Objects.requireNonNull(responseVo).setRoles(roleList);

        //查询角色权限信息
        List<String> permissions = sysMenuService.getPermissionsByUserId(user.getId());
        responseVo.setPermissions(permissions);

        return responseVo;
    }

    @ApiOperation("获取用户信息")
    @GetMapping(value = "/user/getInfo")
    public Result<UserInfoResponseVO> getInfo(@RequestAttribute Long userId) {
        //查询用户信息
        SysUser userDTO = new SysUser();
        userDTO.setUserName(SecurityUtils.getUsername());
        SysUser user = sysUserService.getById(userId);
        UserInfoResponseVO responseVo = BeanUtil.convert(user, UserInfoResponseVO.class);

        //查询角色信息
        List<SysRole> roles = roleService.getRolesByUserId(user.getId());
        String[] role = roles.stream().map(SysRole::getRoleName).toArray(String[]::new);
        Objects.requireNonNull(responseVo).setRoles(role);

        return Result.ok(responseVo);
    }

    @ApiOperation("更新用户信息")
    @PostMapping(value = "/user/updateUser")
    public Result<Void> updateUser(@RequestBody @Validated UserInfoResponseVO vo) {
        SysUser byId = sysUserService.getById(vo.getId());
        BeanUtils.copyProperties(vo, byId, "roles", "id");
        sysUserService.updateById(byId);

        return Result.ok("更新成功");
    }

    @ApiOperation("用户修改密码")
    @PostMapping(value = "/user/updatePwd")
    public Result<Void> updatePwd(@RequestBody @Validated SysUserPwdVO vo, @RequestAttribute Long userId) {
        sysUserService.verifyPassword(vo, userId);
        return Result.ok("修改成功");
    }

    @PostMapping("/user/uploadAvatar")
    public Result<Void> uploadAvatar(HttpServletRequest request) {
        String savePath;
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");// 获取上传文件对象

        String bizPath = request.getParameter("biz");
        if (oConvertUtils.isEmpty(bizPath)) {
            bizPath = CommonConstant.UPLOAD_TYPE_OSS.equals(uploadType) ? "upload" : "";
        }

        if (CommonConstant.UPLOAD_TYPE_LOCAL.equals(uploadType)) {
            savePath = this.uploadLocal(file, bizPath);
        } else {
            savePath = sysBaseAPI.upload(file, bizPath, uploadType);
        }

        if (StrUtil.isNotBlank(savePath)) {
            return Result.ok(savePath);
        }
        return Result.error("上传失败!");
    }

    /**
     * 登录查询用户菜单
     *
     * @return Map
     */
    @ApiOperation("登录查询用户菜单")
    @GetMapping(value = "/user/nav")
    public Map<String, Object> getUserNav() {
        Map<String, Object> result = new HashMap<>(2);
        List<SysMenu> list = sysUserService.getUserNav(SecurityUtils.getUsername());
        List<MenuResponseVO> menuPid = getByPid(list, Long.valueOf("0"));

        if (menuPid.size() > 0) {
            for (MenuResponseVO menu : menuPid) {
                menu.setChildren(getByPid(list, menu.getId()));
            }
        }

        result.put("menuData", menuPid);
        return result;
    }

    private List<MenuResponseVO> getByPid(List<SysMenu> list, Long pid) {
        return list.stream().filter(item -> pid.equals(item.getPid())).map(item -> MenuResponseVO.builder()
                .id(item.getId())
                .name(item.getMenu())
                .path(item.getPath())
                .redirect(item.getRedirect())
                .icon(item.getIcon())
                .target(item.getIsFrame() ? "_blank" : null)
                .hideInMenu(!item.getVisible())
                .sort(item.getSort())
                .isFrame(item.getIsFrame())
                .build()).sorted(Comparator.comparing(MenuResponseVO::getSort)).collect(Collectors.toList());
    }

    /**
     * 本地文件上传
     *
     * @param mf      文件
     * @param bizPath 自定义路径
     * @return String
     */
    private String uploadLocal(MultipartFile mf, String bizPath) {
        try {
            String ctxPath = uploadPath;
            String fileName;
            File file = new File(ctxPath + File.separator + bizPath + File.separator);
            if (!file.exists()) {
                //noinspection ResultOfMethodCallIgnored
                file.mkdirs();
            }
            String orgName = mf.getOriginalFilename();
            if (Objects.requireNonNull(orgName).contains(".")) {
                fileName = orgName.substring(0, orgName.lastIndexOf(".")) + "_" + System.currentTimeMillis() + orgName.substring(orgName.indexOf("."));
            } else {
                fileName = orgName + "_" + System.currentTimeMillis();
            }
            String savePath = file.getPath() + File.separator + fileName;
            File savefile = new File(savePath);
            FileCopyUtils.copy(mf.getBytes(), savefile);
            String dbpath;
            if (oConvertUtils.isNotEmpty(bizPath)) {
                dbpath = bizPath + File.separator + fileName;
            } else {
                dbpath = fileName;
            }
            if (dbpath.contains("\\")) {
                dbpath = dbpath.replace("\\", "/");
            }
            return dbpath;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

}
