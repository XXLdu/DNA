package com.ruoyi.code.controller;

import com.ruoyi.code.domain.Appraisalfile;
import com.ruoyi.code.domain.ProcessCode;
import com.ruoyi.code.domain.Trust;
import com.ruoyi.code.domain.TrustParam;
import com.ruoyi.code.service.IAppraisalfileService;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 鉴定文书（APPRAISALFILE）Controller
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
@Controller
@RequestMapping("/code/appraisalfileCheck")
public class AppraisalfileCheckController extends BaseController
{
    private String prefix = "code/appraisalfileCheck";

    @Autowired
    private ITrustService trustService;

    /**
     * 跳转鉴定文书授权人审核列表
     */
    @RequiresPermissions("code:appraisalfileCheck:sqrCheckView")
    @GetMapping("/sqrCheck")
    public String sqrCheck()
    {
        return prefix + "/sqrCheck";
    }

    /**
     * 查询鉴定文书授权人审核列表
     */
    @RequiresPermissions("code:appraisalfileCheck:sqrCheckList")
    @PostMapping("/sqrCheckList")
    @ResponseBody
    public TableDataInfo sqrCheckList(TrustParam trustParam)
    {
        startPage();

        //设置流程code查询参数
        ArrayList<String> processCode = new ArrayList();
        Collections.addAll(processCode, ProcessCode.dsqrshws,ProcessCode.jgfzrthsqrsh);
        trustParam.setProcessCode(processCode);

        List<Trust> list = trustService.selectTrustList(trustParam);
        return getDataTable(list);
    }

    /**
     * 跳转鉴定文书机构负责人审核列表
     */
    @RequiresPermissions("code:appraisalfileCheck:jgfzrCheckView")
    @GetMapping("/jgfzrCheck")
    public String jgfzrCheck()
    {
        return prefix + "/jgfzrCheck";
    }

    /**
     * 查询鉴定文书机构负责人审核列表
     */
    @RequiresPermissions("code:appraisalfileCheck:jgfzrCheckList")
    @PostMapping("/jgfzrCheckList")
    @ResponseBody
    public TableDataInfo jgfzrCheckList(TrustParam trustParam)
    {
        startPage();

        //设置流程code查询参数
        ArrayList<String> processCode = new ArrayList();
        Collections.addAll(processCode, ProcessCode.djgfzrshws);
        trustParam.setProcessCode(processCode);

        List<Trust> list = trustService.selectTrustList(trustParam);
        return getDataTable(list);
    }

    /**
     * 跳转鉴定文书（补发申请）机构负责人审核列表
     */
    @RequiresPermissions("code:appraisalfileCheck:bfsq_jgfzrCheckView")
    @GetMapping("/bfsq_jgfzrCheck")
    public String bfsq_jgfzrCheck()
    {
        return prefix + "/bfsq_jgfzrCheck";
    }

    /**
     * 查询鉴定文书（补发申请）机构负责人审核列表
     */
    @RequiresPermissions("code:appraisalfileCheck:bfsq_jgfzrCheckList")
    @PostMapping("/bfsq_jgfzrCheckList")
    @ResponseBody
    public TableDataInfo bfsq_jgfzrCheckList(TrustParam trustParam)
    {
        startPage();
        /*trust.setProcessCode(ProcessCode.wtdwdsh);*/
        List<Trust> list = trustService.selectTrustList(trustParam);
        return getDataTable(list);
    }
}
