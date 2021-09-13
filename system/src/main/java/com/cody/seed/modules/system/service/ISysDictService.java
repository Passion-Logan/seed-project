package com.cody.seed.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cody.common.system.vo.DictModel;
import com.cody.common.system.vo.DictQuery;
import com.cody.seed.modules.module.TreeSelectModel;
import com.cody.seed.modules.system.entity.SysDict;
import com.cody.seed.modules.system.entity.SysDictItem;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2021/9/13
 * @lastUpdateUser Administrator
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/13
 */
public interface ISysDictService extends IService<SysDict> {

    List<DictModel> queryDictItemsByCode(String code);

    Map<String, List<DictModel>> queryAllDictItems();

    @Deprecated
    List<DictModel> queryTableDictItemsByCode(String table, String text, String code);

    @Deprecated
    List<DictModel> queryTableDictItemsByCodeAndFilter(String table, String text, String code, String filterSql);

    String queryDictTextByKey(String code, String key);

    @Deprecated
    String queryTableDictTextByKey(String table, String text, String code, String key);

    @Deprecated
    List<String> queryTableDictByKeys(String table, String text, String code, String[] keyArray);

    /**
     * 根据字典类型删除关联表中其对应的数据
     *
     * @param sysDict
     * @return
     */
    boolean deleteByDictId(SysDict sysDict);

    /**
     * 添加一对多
     *
     * @param sysDict         sysDict
     * @param sysDictItemList sysDictItemList
     * @return Integer
     */
    Integer saveMain(SysDict sysDict, List<SysDictItem> sysDictItemList);

    /**
     * 查询所有部门 作为字典信息 id -->value,departName -->text
     *
     * @return DictModel
     */
    List<DictModel> queryAllDepartBackDictModel();

    /**
     * 查询所有用户  作为字典信息 username -->value,realname -->text
     *
     * @return DictModel
     */
    List<DictModel> queryAllUserBackDictModel();

    /**
     * 通过关键字查询字典表
     *
     * @param table   table
     * @param text    text
     * @param code    code
     * @param keyword keyword
     * @return DictModel
     */
    @Deprecated
    List<DictModel> queryTableDictItems(String table, String text, String code, String keyword);

    /**
     * 根据表名、显示字段名、存储字段名 查询树
     *
     * @param table         table
     * @param text          text
     * @param code          code
     * @param pidField      pidField
     * @param pid           pid
     * @param hasChildField hasChildField
     * @return TreeSelectModel
     */
    @Deprecated
    List<TreeSelectModel> queryTreeList(Map<String, String> query, String table, String text, String code, String pidField, String pid, String hasChildField);

    /**
     * 真实删除
     *
     * @param id id
     */
    void deleteOneDictPhysically(String id);

    /**
     * 修改delFlag
     *
     * @param delFlag delFlag
     * @param id      id
     */
    void updateDictDelFlag(int delFlag, String id);

    /**
     * 查询被逻辑删除的数据
     *
     * @return SysDict
     */
    List<SysDict> queryDeleteList();

    /**
     * 分页查询
     *
     * @param query    query
     * @param pageSize pageSize
     * @param pageNo   pageNo
     * @return DictModel
     */
    @Deprecated
    List<DictModel> queryDictTablePageList(DictQuery query, int pageSize, int pageNo);

}
