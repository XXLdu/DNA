package com.ruoyi.code.controller;

import java.util.List;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.ruoyi.code.domain.FileData;
import com.ruoyi.code.domain.Sample;
import com.ruoyi.code.service.ISampleService;
import com.ruoyi.code.service.ISysFileInfoService;
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
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 检材Controller
 * 
 * @author dqs
 * @date 2020-07-08
 */
@Controller
@RequestMapping("/code/sample")
public class SampleController extends BaseController
{
    private String prefix = "code/sample";

    @Autowired
    private ISampleService sampleService;

    @Autowired
    private ISysFileInfoService iSysFileInfoService;

    @RequiresPermissions("code:sample:view")
    @GetMapping("/{trust_id}")
    public String sample(@PathVariable("trust_id") String trust_id, ModelMap mmap)
    {
        mmap.put("trust_id", trust_id);
        return prefix + "/sample";
    }

    /**
     * 查询检材列表
     */
    @RequiresPermissions("code:sample:list")
    @PostMapping("/list/{trust_id}")
    @ResponseBody
    public TableDataInfo list(@PathVariable("trust_id") String trust_id, Sample sample)
    {
        startPage();
        sample.setTrustId(trust_id);
        List<Sample> list = sampleService.selectSampleList(sample);
        return getDataTable(list);
    }

    /**
     * 导出检材列表
     */
    @RequiresPermissions("code:sample:export")
    @Log(title = "检材", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Sample sample)
    {
        List<Sample> list = sampleService.selectSampleList(sample);
        ExcelUtil<Sample> util = new ExcelUtil<Sample>(Sample.class);
        return util.exportExcel(list, "sample");
    }

    /**
     * 新增物证
     */
    @GetMapping("/add/{trust_id}")
    public String add(@PathVariable("trust_id") String trust_id,ModelMap mmap)
    {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        mmap.put("id", uuid);
        mmap.put("trust_id", trust_id+"");
        return prefix + "/add";
    }

    /**
     * 新增保存检材
     */
    @RequiresPermissions("code:sample:add")
    @Log(title = "检材", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Sample sample)
    {
        return toAjax(sampleService.insertSample(sample));
    }

    /**
     * 修改检材
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        Sample sample = sampleService.selectSampleById(id);
        mmap.put("sample", sample);
        return prefix + "/edit";
    }

    /**
     * 修改保存检材
     */
    @RequiresPermissions("code:sample:edit")
    @Log(title = "检材", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Sample sample)
    {
        return toAjax(sampleService.updateSample(sample));
    }

    /**
     * 删除检材
     */
    @RequiresPermissions("code:sample:remove")
    @Log(title = "检材", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sampleService.deleteSampleByIds(ids));
    }

    /**
     * 字符串转unicode
     *
     * @param str
     * @return
     */
    public static String strToUnicode(String str) {
        StringBuilder sb = new StringBuilder();
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            sb.append("\\u" + Integer.toHexString(c[i]));
        }
        return sb.toString();
    }
}
