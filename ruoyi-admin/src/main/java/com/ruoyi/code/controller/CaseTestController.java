package com.ruoyi.code.controller;

import com.ruoyi.code.domain.ProcessCode;
import com.ruoyi.code.domain.Trust;
import com.ruoyi.code.domain.TrustParam;
import com.ruoyi.code.service.ITrustService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 案件检验Controller
 *
 * @author dqs
 * @date 2020年6月4日
 */
@Controller
@RequestMapping("/code/caseTest")
public class CaseTestController extends BaseController
{
    private String prefix = "code/caseTest";

    @Autowired
    private ITrustService trustService;

    /**
     * 跳转案件检验列表
     */
    @RequiresPermissions("code:caseTest:view")
    @GetMapping()
    public String caseTest()
    {
        return prefix+"/caseTest";
    }

    /**
     * 查询案件检验列表
     */
    @RequiresPermissions("code:caseTest:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TrustParam trustParam)
    {
        startPage();

        //设置流程code查询参数
        ArrayList<String> processCode = new ArrayList();
        Collections.addAll(processCode,ProcessCode.djy);
        trustParam.setProcessCode(processCode);

        List<Trust> list = trustService.selectTrustList(trustParam);
        return getDataTable(list);
    }

}
