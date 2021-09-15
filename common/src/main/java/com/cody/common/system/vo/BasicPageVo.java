package com.cody.common.system.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cody.common.constant.CommonConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页返回对象信息
 *
 * @author wql
 * @desc BasicPageVo
 * @date 2021/9/14
 * @lastUpdateUser wql
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BasicPageVo<T> extends BasicVo {

    /**
     * 当前页码
     */
    private Long current = 1L;
    /**
     * 每页数据条数
     */
    private Long size = 0L;
    /**
     * 总页
     */
    private Long totalPage = 1L;
    /**
     * 总长度
     */
    private Long total = 0L;

    /**
     * 查询数据列表
     */
    private List<T> data = Collections.emptyList();

    /**
     * @param page page
     * @param <T>  T
     * @return BasicPageVo
     */
    public static <T> BasicPageVo<T> ofPages(IPage<T> page) {
        BasicPageVo<T> response = getBasicPageVo(page);
        response.setData(page.getRecords());
        return response;
    }

    /**
     * @param page    page
     * @param results 转换后的数据信息
     * @param <T>     T
     * @return BasicPageVo
     */
    public static <T> BasicPageVo<T> ofPages(IPage<?> page, List<T> results) {
        BasicPageVo<T> response = getBasicPageVo(page);
        response.setData(results);
        return response;
    }

    /**
     * @param page    page
     * @param results 转换后的数据信息
     * @param <T>     T
     * @return BasicPageVo
     */
    public static <T, R> BasicPageVo<R> ofPages(IPage<T> page, Function<T, R> results) {
        BasicPageVo<R> response = getBasicPageVo(page);
        response.setData(page.getRecords().stream().map(results).collect(Collectors.toList()));
        return response;
    }

    /**
     * @param page        page
     * @param tClass      tClass
     * @param copyOptions copyOptions
     * @param <T>         T
     * @return BasicPageVo
     */
    public static <T> BasicPageVo<T> ofPages(IPage<?> page, Class<T> tClass, CopyOptions copyOptions) {
        BasicPageVo<T> response = getBasicPageVo(page);
        response.setData(page.getRecords().stream().map(item -> BeanUtil.toBean(item, tClass, copyOptions)).collect(Collectors.toList()));
        return response;
    }

    /**
     * 数据信息转换
     *
     * @param page page
     * @param <T>  T
     * @return response
     */
    private static <T> BasicPageVo<T> getBasicPageVo(IPage<?> page) {
        BasicPageVo<T> response = new BasicPageVo<>();
        response.setSize(page.getSize());
        response.setCurrent(page.getCurrent());
        response.setTotalPage(page.getPages());
        response.setTotal(page.getTotal());
        response.setMessage("操作成功！");
        response.setCode(CommonConstant.SC_OK_200);
        response.setSuccess(true);
        response.setTimestamp(System.currentTimeMillis());
        return response;
    }

}
