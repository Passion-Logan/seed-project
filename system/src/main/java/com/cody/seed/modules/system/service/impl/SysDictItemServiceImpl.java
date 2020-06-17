package com.cody.seed.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cody.seed.modules.system.entity.SysDictItem;
import com.cody.seed.modules.system.mapper.SysDictItemMapper;
import com.cody.seed.modules.system.service.ISysDictItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements ISysDictItemService {

    @Autowired
    private SysDictItemMapper sysDictItemMapper;

    @Override
    public List<SysDictItem> selectItemsByMainId(String mainId) {
        return sysDictItemMapper.selectItemsByMainId(mainId);
    }

}
