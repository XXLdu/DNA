package com.ruoyi.code.controller;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.code.domain.DnaData;
import com.ruoyi.code.domain.Sample;
import com.ruoyi.code.domain.TestrecordDetail;
import com.ruoyi.code.service.ISampleService;
import com.ruoyi.code.service.ITestrecordDetailService;
import com.ruoyi.code.utils.CreatDnaList;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 鉴定详情Controller
 * 
 * @author ruoyi
 * @date 2020-07-18
 */
@Controller
@RequestMapping("/code/testrecordDetail")
public class TestrecordDetailController extends BaseController
{
    private String prefix = "code/testrecordDetail";

    @Autowired
    private ITestrecordDetailService testrecordDetailService;

    @Autowired
    private ISampleService sampleService;

    @RequiresPermissions("code:detail:view")
    @GetMapping("/{trust_id}")
    public String detail(@PathVariable("trust_id") String trust_id,ModelMap mmap)
    {
        mmap.put("trust_id",trust_id);

        Sample samplParam = new Sample();
        samplParam.setTrustId(trust_id);
        List<Sample> sampleList = sampleService.selectSampleList(samplParam);
        mmap.put("sampleList",sampleList);

        return prefix + "/detail";
    }

    @PostMapping("getTestrecordDetailList")
    @ResponseBody
    public String getTestrecordDetailList(String trust_id)
    {
        TestrecordDetail testrecordDetailParam = new TestrecordDetail();
        testrecordDetailParam.setTrustId(trust_id);
        List<TestrecordDetail> testrecordDetailList = testrecordDetailService.selectTestrecordDetailList(testrecordDetailParam);

        return JSONObject.toJSONString(testrecordDetailList);
    }

    /**
     * 查询鉴定详情列表
     */
    @RequiresPermissions("code:detail:list")
    @PostMapping("/list/{trust_id}")
    @ResponseBody
    public TableDataInfo list(@PathVariable("trust_id") String trust_id,TestrecordDetail testrecordDetail)
    {
        startPage();
        testrecordDetail.setTrustId(trust_id);
        List<TestrecordDetail> list = testrecordDetailService.selectTestrecordDetailList(testrecordDetail);
        return getDataTable(list);
    }

    /**
     * 导出鉴定详情列表
     */
    @RequiresPermissions("code:detail:export")
    @Log(title = "鉴定详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TestrecordDetail testrecordDetail)
    {
        List<TestrecordDetail> list = testrecordDetailService.selectTestrecordDetailList(testrecordDetail);
        ExcelUtil<TestrecordDetail> util = new ExcelUtil<TestrecordDetail>(TestrecordDetail.class);
        return util.exportExcel(list, "detail");
    }

    /**
     * 新增鉴定详情
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存鉴定详情
     */
    @RequiresPermissions("code:detail:add")
    @Log(title = "鉴定详情", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TestrecordDetail testrecordDetail)
    {
        return toAjax(testrecordDetailService.insertTestrecordDetail(testrecordDetail));
    }

    /**
     * 保存鉴定详情(多条)
     */
    @RequiresPermissions("code:detail:doSave")
    @Log(title = "鉴定详情", businessType = BusinessType.INSERT)
    @PostMapping("/doSave")
    @ResponseBody
    public int doSave(String json)
    {
        List<TestrecordDetail> list = new ArrayList<TestrecordDetail>();
        list = JSONObject.parseArray(json,TestrecordDetail.class);
        return testrecordDetailService.doSaveTestrecordDetail(list);
    }

    /**
     * 修改鉴定详情
     */
    @GetMapping("/edit/{sampleId}")
    public String edit(@PathVariable("sampleId") String sampleId, ModelMap mmap)
    {
        TestrecordDetail testrecordDetail = testrecordDetailService.selectTestrecordDetailBySampleId(sampleId);
        mmap.put("testrecordDetail", testrecordDetail);
        return prefix + "/edit";
    }


    /**
     * 新增文件信息
     */
    @PostMapping("/datUpload")
    @ResponseBody
    public int addSave(MultipartFile file, String sampleId) throws IOException
    {
        // 上传文件路径
        String filePath = Global.getUploadPath();
        // 上传并返回新文件名称
        String filePath_name = FileUploadUtils.upload(filePath, file);
        String path = (filePath+filePath_name).replace("upload/profile/","");
        List<DnaData> DnaDataList = CreatDnaList.getDnaDataList(path,sampleId);
        return testrecordDetailService.insertDnaDataList(DnaDataList);
    }


    /**
     * 查询DNA列表
     */
    @PostMapping("/selectDnaDataList/{sample_id}")
    @ResponseBody
    public TableDataInfo selectDnaDataList(@PathVariable("sample_id") String sample_id)
    {
        List<DnaData> list = testrecordDetailService.selectDnaDataList(sample_id);
        return getDataTable(list);
    }

    @GetMapping("/dnaDataList/{sample_id}")
    public String dnaDataList(@PathVariable("sample_id") String sample_id,ModelMap mmap)
    {
        mmap.put("sample_id",sample_id);

        return prefix + "/dnaDataList";
    }
}
