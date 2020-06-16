package com.cody.common.system.service;

import com.cody.common.system.dto.PageDTO;

import java.util.List;

public interface BaseService<T> {

    boolean insert(T dto);

    boolean insertInBatch(List<T> dtoList);

    boolean update(T dto);

    boolean delete(T dto);

    <PK> boolean deleteByIds(List<PK> ids);

    List<T> getList(T query);

    PageDTO<T> getList(T dto, int pageNum, int pageSize);

    T get(T dto);

    <PK> T getById(PK pk);

    <PK> List<T> getListByIds(List<PK> pkList);

}
