package com.ruoyi.code.service;

import java.util.List;
import com.ruoyi.code.domain.Rule;

/**
 * 配置表Service接口
 * 
 * @author ruoyi
 * @date 2020-07-19
 */
public interface IRuleService 
{
    /**
     * 查询配置表
     * 
     * @param id 配置表ID
     * @return 配置表
     */
    public Rule selectRuleById(String id);

    /**
     * 查询配置表列表
     * 
     * @param rule 配置表
     * @return 配置表集合
     */
    public List<Rule> selectRuleList(Rule rule);

    /**
     * 新增配置表
     * 
     * @param rule 配置表
     * @return 结果
     */
    public int insertRule(Rule rule);

    /**
     * 修改配置表
     * 
     * @param rule 配置表
     * @return 结果
     */
    public int updateRule(Rule rule);

    /**
     * 批量删除配置表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRuleByIds(String ids);

    /**
     * 删除配置表信息
     * 
     * @param id 配置表ID
     * @return 结果
     */
    public int deleteRuleById(String id);
}
