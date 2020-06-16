package com.cody.common.system.dao;

import java.util.List;

public interface BaseDao<T,PK> {

    int insert(T t);

    int insertInBatch(List<T> list);

    int update(T t);

    int updateInBatch(List<T> list);

    int delete(T t);

    int deleteByIds(List<PK> listIds);

    List<T> getList(T condition);

    T get(T condition);

    T getById(PK id);

    List<T> getListByIds(List<PK> idList);

    <C, R> List<R> getListByCondition(C condition);

}
