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

@Repository
public interface SysDictMapper extends BaseMapper<SysDict> {

    /**
     * 重复检查SQL
     *
     * @return
     */
    public Long duplicateCheckCountSql(DuplicateCheckVo duplicateCheckVo);

    public Long duplicateCheckCountSqlNoDataId(DuplicateCheckVo duplicateCheckVo);

    public List<DictModel> queryDictItemsByCode(@Param("code") String code);

    @Deprecated
    public List<DictModel> queryTableDictItemsByCode(@Param("table") String table, @Param("text") String text, @Param("code") String code);

    @Deprecated
    public List<DictModel> queryTableDictItemsByCodeAndFilter(@Param("table") String table, @Param("text") String text, @Param("code") String code, @Param("filterSql") String filterSql);

    public String queryDictTextByKey(@Param("code") String code, @Param("key") String key);

    @Deprecated
    public String queryTableDictTextByKey(@Param("table") String table, @Param("text") String text, @Param("code") String code, @Param("key") String key);

    @Deprecated
    public List<DictModel> queryTableDictByKeys(@Param("table") String table, @Param("text") String text, @Param("code") String code, @Param("keyArray") String[] keyArray);

    /**
     * 查询所有部门 作为字典信息 id -->value,departName -->text
     *
     * @return
     */
    public List<DictModel> queryAllDepartBackDictModel();

    /**
     * 查询所有用户  作为字典信息 username -->value,realname -->text
     *
     * @return
     */
    public List<DictModel> queryAllUserBackDictModel();

    /**
     * 通过关键字查询出字典表
     *
     * @param table
     * @param text
     * @param code
     * @param keyword
     * @return
     */
    @Deprecated
    public List<DictModel> queryTableDictItems(@Param("table") String table, @Param("text") String text, @Param("code") String code, @Param("keyword") String keyword);

    /**
     * 根据表名、显示字段名、存储字段名 查询树
     *
     * @param table
     * @param text
     * @param code
     * @param pid
     * @param hasChildField
     * @return
     */
    @Deprecated
    List<TreeSelectModel> queryTreeList(@Param("query") Map<String, String> query, @Param("table") String table, @Param("text") String text, @Param("code") String code, @Param("pidField") String pidField, @Param("pid") String pid, @Param("hasChildField") String hasChildField);

    /**
     * 删除
     *
     * @param id
     */
    @Select("delete from sys_dict where id = #{id}")
    public void deleteOneById(@Param("id") String id);

    /**
     * 查询被逻辑删除的数据
     *
     * @return
     */
    @Select("select * from sys_dict where del_flag = 1")
    public List<SysDict> queryDeleteList();

    /**
     * 修改状态值
     *
     * @param delFlag
     * @param id
     */
    @Update("update sys_dict set del_flag = #{flag,jdbcType=INTEGER} where id = #{id,jdbcType=VARCHAR}")
    public void updateDictDelFlag(@Param("flag") int delFlag, @Param("id") String id);


    /**
     * 分页查询字典表数据
     *
     * @param page
     * @param query
     * @return
     */
    @Deprecated
    public Page<DictModel> queryDictTablePageList(Page page, @Param("query") DictQuery query);

}
