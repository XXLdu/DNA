package com.ruoyi.code.controller;

import com.ruoyi.code.domain.Physical;
import com.ruoyi.code.domain.Sample;
import com.ruoyi.code.service.IPhysicalService;
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
 * 物证Controller
 * 
 * @author ruoyi
 * @date 2020-05-31
 */
@Controller
@RequestMapping("/code/physical")
public class PhysicalController extends BaseController
{
    private String prefix = "code/physical";

    @Autowired
    private IPhysicalService physicalService;

    @Autowired
    private ISampleService sampleService;

    /**
     * 委托申请页面的物证iframe页面
     */
    @RequiresPermissions("code:physical:view")
    @GetMapping("/{trust_id}")
    public String physical(@PathVariable("trust_id") String trust_id, ModelMap mmap)
    {
        mmap.put("trust_id", trust_id);
        return prefix + "/physical";
    }

    /**
     * 跳转物品台账页面
     */
    @RequiresPermissions("code:physicalList:view")
    @GetMapping("/physicalList")
    public String physicalList()
    {
        return prefix + "/physicalList";
    }


    /**
     * 查询物证台账列表
     */
    @RequiresPermissions("code:physicalList:list")
    @PostMapping("/physicalList")
    @ResponseBody
    public TableDataInfo list()
    {
        startPage();
        List<Sample> list = sampleService.selectSampleListByProcessCode();
        return getDataTable(list);
    }

    /**
     * 查询物证列表
     */
    @RequiresPermissions("code:physical:list")
    @PostMapping("/list/{trust_id}")
    @ResponseBody
    public TableDataInfo list(@PathVariable("trust_id") String trust_id, Physical physical)
    {
        startPage();
        physical.setTrustId(trust_id);
        List<Physical> list = physicalService.selectPhysicalList(physical);
        return getDataTable(list);
    }

    /**
     * 导出物证列表
     */
    @RequiresPermissions("code:physical:export")
    @Log(title = "物证", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Physical physical)
    {
        List<Physical> list = physicalService.selectPhysicalList(physical);
        ExcelUtil<Physical> util = new ExcelUtil<Physical>(Physical.class);
        return util.exportExcel(list, "physical");
    }

    /**
     * 新增物证
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
     * 新增保存物证
     */
    @RequiresPermissions("code:physical:add")
    @Log(title = "物证", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Physical physical)
    {
        return toAjax(physicalService.insertPhysical(physical));
    }

    /**
     * 修改物证
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        Physical physical = physicalService.selectPhysicalById(id);
        mmap.put("physical", physical);
        return prefix + "/edit";
    }

    /**
     * 修改保存物证
     */
    @RequiresPermissions("code:physical:edit")
    @Log(title = "物证", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Physical physical)
    {
        return toAjax(physicalService.updatePhysical(physical));
    }

    /**
     * 删除物证
     */
    @RequiresPermissions("code:physical:remove")
    @Log(title = "物证", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(physicalService.deletePhysicalByIds(ids));
    }
}
