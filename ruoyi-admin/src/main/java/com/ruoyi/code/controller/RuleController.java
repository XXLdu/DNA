package com.ruoyi.code.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.code.domain.Rule;
import com.ruoyi.code.service.IRuleService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 配置表Controller
 * 
 * @author ruoyi
 * @date 2020-07-19
 */
@Controller
@RequestMapping("/code/rule")
public class RuleController extends BaseController
{
    private String prefix = "code/rule";

    @Autowired
    private IRuleService ruleService;

    @RequiresPermissions("code:rule:view")
    @GetMapping()
    public String rule()
    {
        return prefix + "/rule";
    }

    /**
     * 查询配置表列表
     */
    @RequiresPermissions("code:rule:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Rule rule)
    {
        startPage();
        List<Rule> list = ruleService.selectRuleList(rule);
        return getDataTable(list);
    }

    /**
     * 导出配置表列表
     */
    @RequiresPermissions("code:rule:export")
    @Log(title = "配置表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Rule rule)
    {
        List<Rule> list = ruleService.selectRuleList(rule);
        ExcelUtil<Rule> util = new ExcelUtil<Rule>(Rule.class);
        return util.exportExcel(list, "rule");
    }

    /**
     * 新增配置表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存配置表
     */
    @RequiresPermissions("code:rule:add")
    @Log(title = "配置表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Rule rule)
    {
        return toAjax(ruleService.insertRule(rule));
    }

    /**
     * 修改配置表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        Rule rule = ruleService.selectRuleById(id);
        mmap.put("rule", rule);
        return prefix + "/edit";
    }

    /**
     * 修改保存配置表
     */
    @RequiresPermissions("code:rule:edit")
    @Log(title = "配置表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Rule rule)
    {
        return toAjax(ruleService.updateRule(rule));
    }

    /**
     * 删除配置表
     */
    @RequiresPermissions("code:rule:remove")
    @Log(title = "配置表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(ruleService.deleteRuleByIds(ids));
    }
}
