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
import com.ruoyi.code.domain.Dna;
import com.ruoyi.code.service.IDnaService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * DNA图谱信息（DNA）Controller
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
@Controller
@RequestMapping("/code/dna")
public class DnaController extends BaseController
{
    private String prefix = "code/dna";

    @Autowired
    private IDnaService dnaService;

    @RequiresPermissions("code:dna:view")
    @GetMapping("/{trust_id}")
    public String dna(@PathVariable("trust_id") String trust_id, ModelMap mmap)
    {
        mmap.put("trust_id", trust_id);
        return prefix + "/dna";
    }

    /**
     * 查询DNA图谱信息（DNA）列表
     */
    @RequiresPermissions("code:dna:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Dna dna)
    {
        startPage();
        List<Dna> list = dnaService.selectDnaList(dna);
        return getDataTable(list);
    }

    /**
     * 导出DNA图谱信息（DNA）列表
     */
    @RequiresPermissions("code:dna:export")
    @Log(title = "DNA图谱信息（DNA）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Dna dna)
    {
        List<Dna> list = dnaService.selectDnaList(dna);
        ExcelUtil<Dna> util = new ExcelUtil<Dna>(Dna.class);
        return util.exportExcel(list, "dna");
    }

    /**
     * 新增DNA图谱信息（DNA）
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存DNA图谱信息（DNA）
     */
    @RequiresPermissions("code:dna:add")
    @Log(title = "DNA图谱信息（DNA）", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Dna dna)
    {
        return toAjax(dnaService.insertDna(dna));
    }

    /**
     * 修改DNA图谱信息（DNA）
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        Dna dna = dnaService.selectDnaById(id);
        mmap.put("dna", dna);
        return prefix + "/edit";
    }

    /**
     * 修改保存DNA图谱信息（DNA）
     */
    @RequiresPermissions("code:dna:edit")
    @Log(title = "DNA图谱信息（DNA）", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Dna dna)
    {
        return toAjax(dnaService.updateDna(dna));
    }

    /**
     * 删除DNA图谱信息（DNA）
     */
    @RequiresPermissions("code:dna:remove")
    @Log(title = "DNA图谱信息（DNA）", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(dnaService.deleteDnaByIds(ids));
    }
}
