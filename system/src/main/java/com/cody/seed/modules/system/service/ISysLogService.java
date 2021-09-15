package com.cody.seed.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cody.common.system.vo.BasicPageVo;
import com.cody.seed.modules.system.entity.SysLog;
import com.cody.seed.modules.vo.request.SysLogQueryVO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统日志表 服务类
 *
 * @author Administrator
 * @date 2021/9/13
 * @lastUpdateUser Administrator
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/13
 */
public interface ISysLogService extends IService<SysLog> {

    /**
     * 清空所有日志记录
     */
    void removeAll();

    /**
     * 获取系统总访问次数
     *
     * @return Long
     */
    Long findTotalVisitCount();

    /**
     * 获取系统今日访问次数
     *
     * @return Long
     */
    Long findTodayVisitCount(Date dayStart, Date dayEnd);

    /**
     * 获取系统今日访问 IP数
     *
     * @return Long
     */
    Long findTodayIp(Date dayStart, Date dayEnd);

    /**
     * 首页：根据时间统计访问数量/ip数量
     *
     * @param dayStart dayStart
     * @param dayEnd   dayEnd
     * @return Map
     */
    List<Map<String, Object>> findVisitCount(Date dayStart, Date dayEnd);


    /**
     * 分页获取操作日志
     *
     * @param vo
     * @return
     */
    BasicPageVo<SysLog> getByPage(SysLogQueryVO vo);


}
