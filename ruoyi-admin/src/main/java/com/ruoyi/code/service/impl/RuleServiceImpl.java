package com.ruoyi.code.service.impl;

import com.ruoyi.code.domain.Rule;
import com.ruoyi.code.mapper.RuleMapper;
import com.ruoyi.code.service.IRuleService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 配置表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-19
 */
@Service
public class RuleServiceImpl implements IRuleService 
{
    @Autowired
    private RuleMapper ruleMapper;

    /**
     * 查询配置表
     * 
     * @param id 配置表ID
     * @return 配置表
     */
    @Override
    public Rule selectRuleById(String id)
    {
        return ruleMapper.selectRuleById(id);
    }

    /**
     * 查询配置表列表
     * 
     * @param rule 配置表
     * @return 配置表
     */
    @Override
    public List<Rule> selectRuleList(Rule rule)
    {
        return ruleMapper.selectRuleList(rule);
    }

    /**
     * 新增配置表
     * 
     * @param rule 配置表
     * @return 结果
     */
    @Override
    public int insertRule(Rule rule)
    {
        return ruleMapper.insertRule(rule);
    }

    /**
     * 修改配置表
     * 
     * @param rule 配置表
     * @return 结果
     */
    @Override
    public int updateRule(Rule rule)
    {
        return ruleMapper.updateRule(rule);
    }

    /**
     * 删除配置表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRuleByIds(String ids)
    {
        return ruleMapper.deleteRuleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除配置表信息
     * 
     * @param id 配置表ID
     * @return 结果
     */
    @Override
    public int deleteRuleById(String id)
    {
        return ruleMapper.deleteRuleById(id);
    }
}
