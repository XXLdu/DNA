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
import com.ruoyi.code.domain.Consultreport;
import com.ruoyi.code.service.IConsultreportService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 会诊报告（CONSULTREPORT）Controller
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
@Controller
@RequestMapping("/code/consultreport")
public class ConsultreportController extends BaseController
{
    private String prefix = "code/consultreport";

    @Autowired
    private IConsultreportService consultreportService;

    @RequiresPermissions("code:consultreport:view")
    @GetMapping("/{trust_id}")
    public String consultreport(@PathVariable("trust_id") String trust_id, ModelMap mmap)
    {
        mmap.put("trust_id", trust_id);
        return prefix + "/consultreport";
    }

    /**
     * 查询会诊报告（CONSULTREPORT）列表
     */
    @RequiresPermissions("code:consultreport:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Consultreport consultreport)
    {
        startPage();
        List<Consultreport> list = consultreportService.selectConsultreportList(consultreport);
        return getDataTable(list);
    }

    /**
     * 导出会诊报告（CONSULTREPORT）列表
     */
    @RequiresPermissions("code:consultreport:export")
    @Log(title = "会诊报告（CONSULTREPORT）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Consultreport consultreport)
    {
        List<Consultreport> list = consultreportService.selectConsultreportList(consultreport);
        ExcelUtil<Consultreport> util = new ExcelUtil<Consultreport>(Consultreport.class);
        return util.exportExcel(list, "consultreport");
    }

    /**
     * 新增会诊报告（CONSULTREPORT）
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存会诊报告（CONSULTREPORT）
     */
    @RequiresPermissions("code:consultreport:add")
    @Log(title = "会诊报告（CONSULTREPORT）", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Consultreport consultreport)
    {
        return toAjax(consultreportService.insertConsultreport(consultreport));
    }

    /**
     * 修改会诊报告（CONSULTREPORT）
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        Consultreport consultreport = consultreportService.selectConsultreportById(id);
        mmap.put("consultreport", consultreport);
        return prefix + "/edit";
    }

    /**
     * 修改保存会诊报告（CONSULTREPORT）
     */
    @RequiresPermissions("code:consultreport:edit")
    @Log(title = "会诊报告（CONSULTREPORT）", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Consultreport consultreport)
    {
        return toAjax(consultreportService.updateConsultreport(consultreport));
    }

    /**
     * 删除会诊报告（CONSULTREPORT）
     */
    @RequiresPermissions("code:consultreport:remove")
    @Log(title = "会诊报告（CONSULTREPORT）", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(consultreportService.deleteConsultreportByIds(ids));
    }
}
