package com.cody.seed.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cody.seed.modules.system.entity.SysDictItem;

import java.util.List;

/**
 * @author Administrator
 * @date 2021/9/13
 * @lastUpdateUser Administrator
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/13
 */
public interface ISysDictItemService extends IService<SysDictItem> {

    List<SysDictItem> selectItemsByMainId(String mainId);

}
