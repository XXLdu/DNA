package com.ruoyi.code.controller;

import com.ruoyi.code.domain.ProcessCode;
import com.ruoyi.code.domain.Suggestion;
import com.ruoyi.code.domain.Trust;
import com.ruoyi.code.domain.TrustParam;
import com.ruoyi.code.service.ITrustService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.DocUtil;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.web.controller.common.CommonController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 委托Controller
 *
 * @author ruoyi
 * @date 2020-05-25
 */
@Controller
@RequestMapping("/code/trust")
public class TrustController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);
    private String prefix = "code/trust";
    @Autowired
    private ITrustService trustService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private ISysUserService sysUserService;

    @RequiresPermissions("code:trust:view")
    @GetMapping()
    public String trust()
    {
        return prefix + "/trust";
    }

    /**
     * 查询委托列表
     */
    @RequiresPermissions("code:trust:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TrustParam trustParam)
    {
        startPage();

        /*//设置流程code查询参数
        ArrayList<String> processCode = new ArrayList();
        Collections.addAll(processCode,ProcessCode.wtj,ProcessCode.wtdwth,ProcessCode.dectj,ProcessCode.jddwth);
        trustParam.setProcessCode(processCode);*/

        List<Trust> list = trustService.selectTrustList(trustParam);
        return getDataTable(list);
    }

    /**
     * 导出委托列表
     */
    @RequiresPermissions("code:trust:export")
    @Log(title = "委托", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TrustParam trustParam)
    {
        //设置流程code查询参数
        ArrayList<String> processCode = new ArrayList();
        Collections.addAll(processCode,ProcessCode.wtj,ProcessCode.wtdwth,ProcessCode.dectj,ProcessCode.jddwth);
        trustParam.setProcessCode(processCode);

        List<Trust> list = trustService.selectTrustList(trustParam);
        ExcelUtil<Trust> util = new ExcelUtil<Trust>(Trust.class);
        return util.exportExcel(list, "trust");
    }

    /**
     * 新增委托
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String timeStr = df.format(new Date());
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        mmap.put("time",timeStr);
        mmap.put("id", uuid);

        SysUser user = sysUserService.selectUserById(ShiroUtils.getSysUser().getUserId());
        mmap.put("departmentId", StringUtils.nvl(user.getDeptId(),""));
        mmap.put("departmentName", StringUtils.nvl(user.getDept().getDeptName(),""));
        mmap.put("name1", StringUtils.nvl(user.getUserName(),""));
        mmap.put("tel1", StringUtils.nvl(user.getPhonenumber(),""));
        mmap.put("job1", StringUtils.nvl(user.getJob(),""));
        mmap.put("cardType1", StringUtils.nvl(user.getCardType(),""));
        mmap.put("cardCode1", StringUtils.nvl(user.getCardCode(),""));

        SysUser user2 = new SysUser();
        user2.setDeptId(user.getDeptId());
        List<SysUser> deptUserList = sysUserService.selectUserList(user2);
        mmap.put("deptUserList", deptUserList);

        mmap.put("appraisalAsk", "DNA检验。");
        mmap.put("appraisalWayAsk", "按照行标GA 765-2008、GA 766-2008、GA/T 383-2014、GA/T 1163-2014进行检验分析。");
        return prefix + "/add";
    }

    /**
     * 新增保存委托
     */
    @RequiresPermissions("code:trust:add")
    @Log(title = "委托", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Trust trust)
    {
        trust.setCode(trustService.getTrustCode());//系统生成编号：日期yyyymmdd+流水号001
        trust.setDepartmentName(ShiroUtils.getSysUser().getDept().getDeptName());
        trust.setDepartmentId(ShiroUtils.getSysUser().getDept().getDeptId()+"");
        trust.setProcessCode(ProcessCode.wtj);//流程标识初始默认值为0
        return toAjax(trustService.insertTrust(trust));
    }
    /**
     * 查看详情
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
        Trust trust = trustService.selectTrustById(id);
        mmap.put("trust", trust);
        return prefix + "/detail";
    }

    /**
     * 修改委托
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        Trust trust = trustService.selectTrustById(id);
        mmap.put("trust", trust);

        SysUser user2 = new SysUser();
        user2.setDeptId(Long.parseLong(trust.getDepartmentId()));
        List<SysUser> deptUserList = sysUserService.selectUserList(user2);
        mmap.put("deptUserList", deptUserList);

        return prefix + "/edit";
    }

    /**
     * 修改保存委托
     */
    @RequiresPermissions("code:trust:edit")
    @Log(title = "委托", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Trust trust)
    {
        return toAjax(trustService.updateTrust(trust));
    }

    /**
     * 删除委托
     */
    @RequiresPermissions("code:trust:remove")
    @Log(title = "委托", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(trustService.deleteTrustByIds(ids));
    }

    /**
     * 委托流程
     */
    @RequiresPermissions("code:trust:process")
    @Log(title = "委托流程")
    @PostMapping( "/trustProcess")
    @ResponseBody
    public AjaxResult trustProcess(Suggestion suggestion)
    {
        return toAjax(trustService.trustProcess(suggestion));
    }

    /**
     * 审核退回审核意见弹窗
     */
    @GetMapping("/backModal/{id}")
    public String backModal(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("parentid", id);
        return prefix + "/backModal";
    }

    /**
     * 下载文件
     */
    @GetMapping("template/{id}/{processCode}")
    public void template(@PathVariable("id") String id,
                           @PathVariable("processCode") String processCode,
                           HttpServletResponse response,
                           HttpServletRequest request)
    {
        String srcName = "";
        switch (processCode) {
            case "1":
                srcName =  "/鉴定委托书.docx";
                break;
            case "4":
                srcName = "/鉴定事项确认书.docx";
                break;
            case "7":
                srcName = "/鉴定文书审批表.docx";
                break;
            default:
                srcName = "/鉴定委托书.docx";
        }
        String filePath = Global.getDownloadPath();
        //获取源文件
        File src = new File(filePath+srcName);
        //创建目标文件
        String dateStr = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());
        String destFileName = srcName.substring(0,srcName.indexOf(".")).trim()+dateStr+srcName.substring(srcName.indexOf(".")).trim();
        DocUtil.createFile(filePath,destFileName);
        //获取目标文件
        File dest = new File(filePath+destFileName);
        //copy源文件内容至目标文件
        try {
            DocUtil.listDicTory(src,dest);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //替换目标文件里的标识符内容
        Trust trust = trustService.selectTrustById(id);
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", trust.getCode());
        map.put("date", new SimpleDateFormat("yyyy.MM.dd").format(trust.getTime()));
        map.put("departmentName", trust.getDepartmentName());
        map.put("name1", trust.getName1());
        map.put("cCode1", trust.getCardCode1());
        map.put("tel1", trust.getTel1());
        map.put("name2", trust.getName2());
        map.put("cCode2", trust.getCardCode2());
        map.put("tel2", trust.getTel2());
        map.put("caseName", trust.getCaseName());
        map.put("caseType", trust.getCaseType());
        map.put("caseSummary", trust.getCaseSummary());
        map.put("appraisalAsk", trust.getAppraisalAsk());
        DocUtil.searchAndReplace(filePath+srcName,filePath+destFileName,map);

        //文件下载
        String fileName = destFileName.substring(1);
        try {
            if (!FileUtils.isValidFilename(fileName)) {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            filePath = Global.getDownloadPath() + fileName;

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, fileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());

            FileUtils.deleteFile(filePath);
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }



    public static void main(String[] args) {
        String filePath = Global.getDownloadPath();
        System.out.println(filePath);
    };


};

