package com.ruoyi.code.controller;

import com.ruoyi.code.domain.PhysicalDeliver;
import com.ruoyi.code.domain.Sample;
import com.ruoyi.code.service.IPhysicalDeliverService;
import com.ruoyi.code.service.ISampleService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 物证流转Controller
 * 
 * @author dqs
 * @date 2020-06-06
 */
@Controller
@RequestMapping("/code/physicaldeliver")
public class PhysicalDeliverController extends BaseController
{
    private String prefix = "code/physicaldeliver";

    @Autowired
    private IPhysicalDeliverService physicalDeliverService;

    @Autowired
    private ISampleService sampleService;

    @RequiresPermissions("code:physicaldeliver:view")
    @GetMapping("/{physical_id}")
    public String physicaldeliver(@PathVariable("physical_id") String physical_id, ModelMap mmap)
    {
        mmap.put("physical_id", physical_id);
        return prefix + "/physicaldeliver";
    }

    /**
     * 查询物证流转列表
     */
    @RequiresPermissions("code:physicaldeliver:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PhysicalDeliver physicalDeliver)
    {
        startPage();
        List<PhysicalDeliver> list = physicalDeliverService.selectPhysicalDeliverList(physicalDeliver);
        return getDataTable(list);
    }

    /**
     * 导出物证流转列表
     */
    @RequiresPermissions("code:physicaldeliver:export")
    @Log(title = "物证流转", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PhysicalDeliver physicalDeliver)
    {
        List<PhysicalDeliver> list = physicalDeliverService.selectPhysicalDeliverList(physicalDeliver);
        ExcelUtil<PhysicalDeliver> util = new ExcelUtil<PhysicalDeliver>(PhysicalDeliver.class);
        return util.exportExcel(list, "physicaldeliver");
    }

    /**
     * 新增物证流转
     */
    @GetMapping("add/{sample_id}")
    public String add(@PathVariable("sample_id") String sample_id, ModelMap mmap)
    {
        Sample sample = sampleService.selectSampleById(sample_id);

        PhysicalDeliver physicalDeliver = new PhysicalDeliver();
        physicalDeliver.setPhysicalId(sample_id);
        List<PhysicalDeliver> list = physicalDeliverService.selectPhysicalDeliverList(physicalDeliver);
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        mmap.put("sample", sample);
        mmap.put("fromPeopleName", list.get(0).getToPeopleName()+"");
        mmap.put("id", uuid);
        return prefix + "/add";
    }

    /**
     * 新增保存物证流转
     */
    @RequiresPermissions("code:physicaldeliver:add")
    @Log(title = "物证流转", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PhysicalDeliver physicalDeliver)
    {
        return toAjax(physicalDeliverService.insertPhysicalDeliver(physicalDeliver));
    }

    /**
     * 修改物证流转
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        PhysicalDeliver physicalDeliver = physicalDeliverService.selectPhysicalDeliverById(id);
        mmap.put("physicalDeliver", physicalDeliver);
        return prefix + "/edit";
    }

    /**
     * 修改保存物证流转
     */
    @RequiresPermissions("code:physicaldeliver:edit")
    @Log(title = "物证流转", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PhysicalDeliver physicalDeliver)
    {
        return toAjax(physicalDeliverService.updatePhysicalDeliver(physicalDeliver));
    }

    /**
     * 删除物证流转
     */
    @RequiresPermissions("code:physicaldeliver:remove")
    @Log(title = "物证流转", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(physicalDeliverService.deletePhysicalDeliverByIds(ids));
    }
}
