package com.ruoyi.code.controller;

import com.ruoyi.code.domain.Rfe;
import com.ruoyi.code.service.IRfeService;
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
 * 补充材料（RFE）Controller
 *
 * @author ruoyi
 * @date 2020-06-06
 */
@Controller
@RequestMapping("/code/rfe")
public class RfeController extends BaseController
{
    private String prefix = "code/rfe";

    @Autowired
    private IRfeService rfeService;

    @RequiresPermissions("code:rfe:view")
    @GetMapping("/{trust_id}")
    public String rfe(@PathVariable("trust_id") String trust_id, ModelMap mmap,String operateType)
    {
        mmap.put("operateType", operateType);
        mmap.put("trust_id", trust_id);
        return prefix + "/rfe";
    }

    /**
     * 查询补充材料（RFE）列表
     */
    @RequiresPermissions("code:rfe:list")
    @PostMapping("/list/{trust_id}")
    @ResponseBody
    public TableDataInfo list(@PathVariable("trust_id") String trust_id,Rfe rfe)
    {
        startPage();
        rfe.setTrustId(trust_id);
        List<Rfe> list = rfeService.selectRfeList(rfe);
        return getDataTable(list);
    }

    /**
     * 导出补充材料（RFE）列表
     */
    @RequiresPermissions("code:rfe:export")
    @Log(title = "补充材料（RFE）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Rfe rfe)
    {
        List<Rfe> list = rfeService.selectRfeList(rfe);
        ExcelUtil<Rfe> util = new ExcelUtil<Rfe>(Rfe.class);
        return util.exportExcel(list, "rfe");
    }

    /**
     * 新增补充材料（RFE）
     */
    @GetMapping("/add/{trust_id}")
    public String add(@PathVariable("trust_id") String trust_id,ModelMap mmap)
    {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        mmap.put("id",uuid);
        mmap.put("trust_id",trust_id);
        return prefix + "/add";
    }

    /**
     * 新增保存补充材料（RFE）
     */
    @RequiresPermissions("code:rfe:add")
    @Log(title = "补充材料（RFE）", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Rfe rfe)
    {
        return toAjax(rfeService.insertRfe(rfe));
    }

    /**
     * 修改补充材料（RFE）
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        Rfe rfe = rfeService.selectRfeById(id);
        mmap.put("rfe", rfe);
        return prefix + "/edit";
    }

    /**
     * 修改保存补充材料（RFE）
     */
    @RequiresPermissions("code:rfe:edit")
    @Log(title = "补充材料（RFE）", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Rfe rfe)
    {
        return toAjax(rfeService.updateRfe(rfe));
    }

    /**
     * 删除补充材料（RFE）
     */
    @RequiresPermissions("code:rfe:remove")
    @Log(title = "补充材料（RFE）", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(rfeService.deleteRfeByIds(ids));
    }
}
