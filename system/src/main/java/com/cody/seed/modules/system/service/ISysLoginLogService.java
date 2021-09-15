package com.cody.seed.modules.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cody.common.system.vo.BasicPageVo;
import com.cody.seed.modules.system.entity.SysLoginLog;
import com.cody.seed.modules.vo.request.SysLoginLogQueryVO;

/**
 * 系统访问记录
 *
 * @author Administrator
 * @date 2021/9/13
 * @lastUpdateUser Administrator
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/13
 */
public interface ISysLoginLogService extends IService<SysLoginLog> {

    /**
     * 分页获取操作日志
     *
     * @param vo
     * @return
     */
    BasicPageVo<SysLoginLog> getByPage(SysLoginLogQueryVO vo);

}
