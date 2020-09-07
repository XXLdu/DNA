package com.ruoyi.code.controller;

import java.util.List;
import java.util.UUID;

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
import com.ruoyi.code.domain.Appraisedtor;
import com.ruoyi.code.service.IAppraisedtorService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 被鉴定人Controller
 * 
 * @author ruoyi
 * @date 2020-05-31
 */
@Controller
@RequestMapping("/code/appraisedtor")
public class AppraisedtorController extends BaseController
{
    private String prefix = "code/appraisedtor";

    @Autowired
    private IAppraisedtorService appraisedtorService;

    @RequiresPermissions("code:appraisedtor:view")
    @GetMapping("/{trust_id}")
    public String appraisedtor(@PathVariable("trust_id") String trust_id, ModelMap mmap)
    {
        mmap.put("trust_id", trust_id);
        return prefix + "/appraisedtor";
    }
    /**
     * 查询被鉴定人列表
     */
    @RequiresPermissions("code:appraisedtor:list")
    @PostMapping("/list/{trust_id}")
    @ResponseBody
    public TableDataInfo list(@PathVariable("trust_id") String trust_id, Appraisedtor appraisedtor)
    {
        startPage();
        appraisedtor.setTrustId(trust_id);
        List<Appraisedtor> list = appraisedtorService.selectAppraisedtorList(appraisedtor);
        return getDataTable(list);
    }

    /**
     * 导出被鉴定人列表
     */
    @RequiresPermissions("code:appraisedtor:export")
    @Log(title = "被鉴定人", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Appraisedtor appraisedtor)
    {
        List<Appraisedtor> list = appraisedtorService.selectAppraisedtorList(appraisedtor);
        ExcelUtil<Appraisedtor> util = new ExcelUtil<Appraisedtor>(Appraisedtor.class);
        return util.exportExcel(list, "appraisedtor");
    }

    /**
     * 新增被鉴定人
     */
    @GetMapping("/add/{trust_id}")
    public String add(@PathVariable("trust_id") String trust_id,ModelMap mmap)
    {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        mmap.put("id", uuid);
        mmap.put("trust_id", trust_id);
        return prefix + "/add";
    }

    /**
     * 新增保存被鉴定人
     */
    @RequiresPermissions("code:appraisedtor:add")
    @Log(title = "被鉴定人", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Appraisedtor appraisedtor)
    {
        return toAjax(appraisedtorService.insertAppraisedtor(appraisedtor));
    }

    /**
     * 修改被鉴定人
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        Appraisedtor appraisedtor = appraisedtorService.selectAppraisedtorById(id);
        mmap.put("appraisedtor", appraisedtor);
        return prefix + "/edit";
    }

    /**
     * 修改保存被鉴定人
     */
    @RequiresPermissions("code:appraisedtor:edit")
    @Log(title = "被鉴定人", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Appraisedtor appraisedtor)
    {
        return toAjax(appraisedtorService.updateAppraisedtor(appraisedtor));
    }

    /**
     * 删除被鉴定人
     */
    @RequiresPermissions("code:appraisedtor:remove")
    @Log(title = "被鉴定人", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(appraisedtorService.deleteAppraisedtorByIds(ids));
    }
}
