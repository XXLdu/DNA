package com.ruoyi.code.controller;

import com.ruoyi.code.domain.*;
import com.ruoyi.code.service.IAppraisalfileService;
import com.ruoyi.code.service.IRuleService;
import com.ruoyi.code.service.ITrustService;
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

import java.util.*;

/**
 * 鉴定文书（APPRAISALFILE）Controller
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
@Controller
@RequestMapping("/code/appraisalfile")
public class AppraisalfileController extends BaseController
{
    private String prefix = "code/appraisalfile";

    @Autowired
    private IAppraisalfileService appraisalfileService;
    @Autowired
    private ITrustService trustService;
    @Autowired
    private IRuleService ruleService;


    //跳转鉴定文书委托信息列表
    @RequiresPermissions("code:appraisalfile:trust:view")
    @GetMapping("/trust")
    public String trust()
    {
        return prefix + "/trust";
    }

    /**
     * 查询鉴定文书委托信息列表
     */
    @RequiresPermissions("code:appraisalfile:trust:list")
    @PostMapping("/trust/list")
    @ResponseBody
    public TableDataInfo trustList(TrustParam trustParam)
    {
        startPage();

        //设置流程code查询参数
        ArrayList<String> processCode = new ArrayList();
        Collections.addAll(processCode, ProcessCode.dscjdws,ProcessCode.sqrth,ProcessCode.jgfzrthdsc);
        trustParam.setProcessCode(processCode);

        List<Trust> list = trustService.selectTrustList(trustParam);
        return getDataTable(list);
    }

    //跳转文书起草列表
    @RequiresPermissions("code:appraisalfile:view")
    @GetMapping("draftDoc/{trust_id}")
    public String draftDoc(@PathVariable("trust_id") String trust_id, ModelMap mmap)
    {
        Appraisalfile appraisalfile = new Appraisalfile();
        appraisalfile.setTrustId(trust_id);
        List<Appraisalfile> appraisalfileList = appraisalfileService.selectAppraisalfileList(appraisalfile);
        if(appraisalfileList!=null && !appraisalfileList.isEmpty()){
            appraisalfile= appraisalfileList.get(0);
            mmap.put("appraisalfile",appraisalfile);
        }else{
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            Rule ruleParam = ruleService.selectRuleById("DNACONTENT");
            appraisalfile.setTrustId(trust_id);
            appraisalfile.setId(uuid);
            appraisalfile.setContent(ruleParam.getLongcontent());
            mmap.put("appraisalfile",appraisalfile);
        }
        return prefix + "/draftDoc";
    }

    //跳转鉴定文书列表
    @RequiresPermissions("code:appraisalfile:view")
    @GetMapping("/{trust_id}")
    public String appraisalfile(@PathVariable("trust_id") String trust_id, ModelMap mmap)
    {
        mmap.put("trust_id", trust_id);
        return prefix + "/appraisalfile";
    }

    /**
     * 查询鉴定文书（APPRAISALFILE）列表
     */
    @RequiresPermissions("code:appraisalfile:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Appraisalfile appraisalfile)
    {
        startPage();
        List<Appraisalfile> list = appraisalfileService.selectAppraisalfileList(appraisalfile);
        return getDataTable(list);
    }

    /**
     * 导出鉴定文书（APPRAISALFILE）列表
     */
    @RequiresPermissions("code:appraisalfile:export")
    @Log(title = "鉴定文书（APPRAISALFILE）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Appraisalfile appraisalfile)
    {
        List<Appraisalfile> list = appraisalfileService.selectAppraisalfileList(appraisalfile);
        ExcelUtil<Appraisalfile> util = new ExcelUtil<Appraisalfile>(Appraisalfile.class);
        return util.exportExcel(list, "appraisalfile");
    }

    /**
     * 新增鉴定文书（APPRAISALFILE）
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存鉴定文书（APPRAISALFILE）
     */
    @RequiresPermissions("code:appraisalfile:add")
    @Log(title = "鉴定文书（APPRAISALFILE）", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Appraisalfile appraisalfile)
    {
        return toAjax(appraisalfileService.insertAppraisalfile(appraisalfile));
    }

    /**
     * 修改鉴定文书（APPRAISALFILE）
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        Appraisalfile appraisalfile = appraisalfileService.selectAppraisalfileById(id);
        mmap.put("appraisalfile", appraisalfile);
        return prefix + "/edit";
    }

    /**
     * 修改保存鉴定文书（APPRAISALFILE）
     */
    @RequiresPermissions("code:appraisalfile:edit")
    @Log(title = "鉴定文书（APPRAISALFILE）", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Appraisalfile appraisalfile)
    {
        return toAjax(appraisalfileService.updateAppraisalfile(appraisalfile));
    }

    /**
     * 保存鉴定文书（APPRAISALFILE）
     */
    @RequiresPermissions("code:appraisalfile:edit")
    @Log(title = "鉴定文书（APPRAISALFILE）", businessType = BusinessType.UPDATE)
    @PostMapping("/doSave")
    @ResponseBody
    public AjaxResult doSave(Appraisalfile appraisalfile)
    {
        List<Appraisalfile> appraisalfileList = appraisalfileService.selectAppraisalfileList(appraisalfile);
        if(appraisalfileList!=null && !appraisalfileList.isEmpty()){
            return toAjax(appraisalfileService.updateAppraisalfile(appraisalfile));
        }else{
            appraisalfile.setTime(new Date());
            return toAjax(appraisalfileService.insertAppraisalfile(appraisalfile));
        }
    }


    /**
     * 删除鉴定文书（APPRAISALFILE）
     */
    @RequiresPermissions("code:appraisalfile:remove")
    @Log(title = "鉴定文书（APPRAISALFILE）", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(appraisalfileService.deleteAppraisalfileByIds(ids));
    }
}
