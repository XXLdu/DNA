package com.ruoyi.code.controller;

import com.ruoyi.code.domain.BloodCard;
import com.ruoyi.code.service.IBloodCardService;
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
 * 血卡Controller
 * 
 * @author ruoyi
 * @date 2020-07-19
 */
@Controller
@RequestMapping("/code/card")
public class BloodCardController extends BaseController
{
    private String prefix = "code/card";

    @Autowired
    private IBloodCardService bloodCardService;

    @RequiresPermissions("code:card:view")
    @GetMapping()
    public String card()
    {
        return prefix + "/card";
    }

    /**
     * 查询血卡列表
     */
    @RequiresPermissions("code:card:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BloodCard bloodCard)
    {
        startPage();
        List<BloodCard> list = bloodCardService.selectBloodCardList(bloodCard);
        return getDataTable(list);
    }

    /**
     * 导出血卡列表
     */
    @RequiresPermissions("code:card:export")
    @Log(title = "血卡", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BloodCard bloodCard)
    {
        List<BloodCard> list = bloodCardService.selectBloodCardList(bloodCard);
        ExcelUtil<BloodCard> util = new ExcelUtil<BloodCard>(BloodCard.class);
        return util.exportExcel(list, "card");
    }

    /**
     * 新增血卡
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存血卡
     */
    @RequiresPermissions("code:card:add")
    @Log(title = "血卡", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BloodCard bloodCard)
    {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        bloodCard.setId(uuid);
        return toAjax(bloodCardService.insertBloodCard(bloodCard));
    }

    /**
     * 修改血卡
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BloodCard bloodCard = bloodCardService.selectBloodCardById(id);
        mmap.put("bloodCard", bloodCard);
        return prefix + "/edit";
    }

    /**
     * 修改保存血卡
     */
    @RequiresPermissions("code:card:edit")
    @Log(title = "血卡", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BloodCard bloodCard)
    {
        return toAjax(bloodCardService.updateBloodCard(bloodCard));
    }

    /**
     * 删除血卡
     */
    @RequiresPermissions("code:card:remove")
    @Log(title = "血卡", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bloodCardService.deleteBloodCardByIds(ids));
    }
}
