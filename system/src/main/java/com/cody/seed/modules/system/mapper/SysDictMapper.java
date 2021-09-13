package com.cody.seed.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cody.common.system.vo.DictModel;
import com.cody.common.system.vo.DictQuery;
import com.cody.seed.modules.module.DuplicateCheckVo;
import com.cody.seed.modules.module.TreeSelectModel;
import com.cody.seed.modules.system.entity.SysDict;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2021/9/13
 * @lastUpdateUser Administrator
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/13
 */
@SuppressWarnings("ALL")
@Repository
public interface SysDictMapper extends BaseMapper<SysDict> {

    /**
     * 重复检查SQL
     *
     * @return Long
     */
    Long duplicateCheckCountSql(DuplicateCheckVo duplicateCheckVo);

    Long duplicateCheckCountSqlNoDataId(DuplicateCheckVo duplicateCheckVo);

    List<DictModel> queryDictItemsByCode(@Param("code") String code);

    @Deprecated
    List<DictModel> queryTableDictItemsByCode(@Param("table") String table, @Param("text") String text, @Param("code") String code);

    @Deprecated
    List<DictModel> queryTableDictItemsByCodeAndFilter(@Param("table") String table, @Param("text") String text, @Param("code") String code, @Param("filterSql") String filterSql);

    String queryDictTextByKey(@Param("code") String code, @Param("key") String key);

    @Deprecated
    String queryTableDictTextByKey(@Param("table") String table, @Param("text") String text, @Param("code") String code, @Param("key") String key);

    @Deprecated
    List<DictModel> queryTableDictByKeys(@Param("table") String table, @Param("text") String text, @Param("code") String code, @Param("keyArray") String[] keyArray);

    /**
     * 查询所有部门 作为字典信息 id -->value,departName -->text
     *
     * @return DictModel
     */
    List<DictModel> queryAllDepartBackDictModel();

    /**
     * 查询所有用户  作为字典信息 username -->value,realname -->text
     *
     * @return
     */
    List<DictModel> queryAllUserBackDictModel();

    /**
     * 通过关键字查询出字典表
     *
     * @param table   table
     * @param text    text
     * @param code    code
     * @param keyword keyword
     * @return DictModel
     */
    @Deprecated
    List<DictModel> queryTableDictItems(@Param("table") String table, @Param("text") String text, @Param("code") String code, @Param("keyword") String keyword);

    /**
     * 根据表名、显示字段名、存储字段名 查询树
     *
     * @param table         table
     * @param text          text
     * @param code          code
     * @param pid           pid
     * @param hasChildField hasChildField
     * @return TreeSelectModel
     */
    @Deprecated
    List<TreeSelectModel> queryTreeList(@Param("query") Map<String, String> query, @Param("table") String table, @Param("text") String text, @Param("code") String code, @Param("pidField") String pidField, @Param("pid") String pid, @Param("hasChildField") String hasChildField);

    /**
     * 删除
     *
     * @param id id
     */
    @Select("delete from sys_dict where id = #{id}")
    void deleteOneById(@Param("id") String id);

    /**
     * 查询被逻辑删除的数据
     *
     * @return SysDict
     */
    @Select("select * from sys_dict where del_flag = 1")
    List<SysDict> queryDeleteList();

    /**
     * 修改状态值
     *
     * @param delFlag delFlag
     * @param id      id
     */
    @Update("update sys_dict set del_flag = #{flag,jdbcType=INTEGER} where id = #{id,jdbcType=VARCHAR}")
    void updateDictDelFlag(@Param("flag") int delFlag, @Param("id") String id);


    /**
     * 分页查询字典表数据
     *
     * @param page  page
     * @param query query
     * @return DictModel
     */
    @Deprecated
    Page<DictModel> queryDictTablePageList(Page page, @Param("query") DictQuery query);

}
