package com.cody.seed.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cody.seed.modules.system.entity.SysDictItem;

import java.util.List;

public interface ISysDictItemService extends IService<SysDictItem> {

    public List<SysDictItem> selectItemsByMainId(String mainId);

}
