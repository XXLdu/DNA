package com.ruoyi.code.controller;

import com.ruoyi.code.domain.*;
import com.ruoyi.code.service.ISampleService;
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
import com.ruoyi.common.utils.poi.TableInWord;
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
    private ISampleService sampleService;

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
        String filePath = "";
        File directory = new File("");//参数为空
        try {
            filePath = directory.getCanonicalPath()+"\\ruoyi-admin\\src\\main\\resources\\static\\file\\";
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> map = new HashMap<String, String>();

        String srcName = "";
        switch (processCode) {
            case "1":
                srcName =  "/鉴定委托书.doc";
                map = getJdwtsMap(id);
                break;
            case "4":
                srcName = "/鉴定事项确认书.doc";
                break;
            case "7":
                srcName = "/鉴定文书审批表.doc";
                break;
            default:
                srcName = "/鉴定委托书.doc";
        }
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
        //替换文件内容
        DocUtil.searchAndReplaceDoc(filePath+srcName,filePath+destFileName,map);

        //向文件中插入表格
        if(!StringUtils.isNull(map.get("table"))){
            TableInWord.todo(map.get("table")+"",filePath+destFileName);
        }

        //文件下载
        String fileName = destFileName.substring(1);
        try {
            if (!FileUtils.isValidFilename(fileName)) {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String downPath = filePath + fileName;

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, fileName));
            FileUtils.writeBytes(downPath, response.getOutputStream());

            FileUtils.deleteFile(filePath+destFileName);
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }



    private Map getJdwtsMap(String trustId){
        //替换目标文件里的标识符内容
        Trust trust = trustService.selectTrustById(trustId);
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

        Sample sample = new Sample();
        sample.setTrustId(trustId);
        List<Sample> sampleList = sampleService.selectSampleList(sample);
        StringBuffer jcTrBuffer = new StringBuffer();
        for (int i=0;i<sampleList.size();i++) {
            String str  = jcTr;
            sample = sampleList.get(i);
            str.replace("jcxh",(i+1)+"");
            str.replace("jcname",sample.getName());
            str.replace("jcnum",sample.getAmount()+"");
            str.replace("jcxz",sample.getXingzhuang());
            str.replace("jcbzqk",sample.getPackCondition());
            str.replace("jctqbw",sample.getExtractPart());
            str.replace("jctqff",sample.getExtractWay());
            jcTrBuffer.append(str);
        }
        String jcFoot_ = jcFoot.replace("jcsjrqz",trust.getName1());
        String jcTable = jcHander+jcTrBuffer.toString()+jcFoot_;
        map.put("table", jcTable);

        return map;
    }




    public static void main(String[] args) {
        String filePath = Global.getDownloadPath();
        System.out.println(filePath);
    };

    static String jcHander = "<table class=MsoNormalTable border=1 cellspacing=0 cellpadding=0 width=648\n" +
            " style='width:485.8pt;border-collapse:collapse;border:none;mso-border-alt:double windowtext 1.5pt;\n" +
            " mso-padding-alt:0cm 5.4pt 0cm 5.4pt;mso-border-insideh:.75pt solid windowtext;\n" +
            " mso-border-insidev:.75pt solid windowtext'>\n" +
            " <tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes;page-break-inside:avoid;\n" +
            "  height:34.0pt'>\n" +
            "  <td width=35 style='width:26.25pt;border-top:double 1.5pt;border-left:double 1.5pt;\n" +
            "  border-bottom:solid 1.0pt;border-right:solid 1.0pt;border-color:windowtext;\n" +
            "  mso-border-top-alt:double 1.5pt;mso-border-left-alt:double 1.5pt;mso-border-bottom-alt:\n" +
            "  solid .75pt;mso-border-right-alt:solid .75pt;mso-border-color-alt:windowtext;\n" +
            "  padding:0cm 5.4pt 0cm 5.4pt;height:34.0pt'>\n" +
            "  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:\n" +
            "  char'><a name=\"_Hlk51011382\"><span style='mso-bidi-font-size:10.5pt;\n" +
            "  font-family:宋体'>序号<span lang=EN-US><o:p></o:p></span></span></a></p>\n" +
            "  </td>\n" +
            "  <span style='mso-bookmark:_Hlk51011382'></span>\n" +
            "  <td width=131 style='width:98.35pt;border-top:double windowtext 1.5pt;\n" +
            "  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-left-alt:solid windowtext .75pt;mso-border-alt:solid windowtext .75pt;\n" +
            "  mso-border-top-alt:double windowtext 1.5pt;padding:0cm 5.4pt 0cm 5.4pt;\n" +
            "  height:34.0pt'>\n" +
            "  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:\n" +
            "  char'><span style='mso-bookmark:_Hlk51011382'><span style='mso-bidi-font-size:\n" +
            "  10.5pt;font-family:宋体'>名<span lang=EN-US><span\n" +
            "  style='mso-spacerun:yes'>&nbsp;&nbsp; </span></span>称<span lang=EN-US><o:p></o:p></span></span></span></p>\n" +
            "  </td>\n" +
            "  <span style='mso-bookmark:_Hlk51011382'></span>\n" +
            "  <td width=56 style='width:41.75pt;border-top:double windowtext 1.5pt;\n" +
            "  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-left-alt:solid windowtext .75pt;mso-border-alt:solid windowtext .75pt;\n" +
            "  mso-border-top-alt:double windowtext 1.5pt;padding:0cm 5.4pt 0cm 5.4pt;\n" +
            "  height:34.0pt'>\n" +
            "  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:\n" +
            "  char'><span style='mso-bookmark:_Hlk51011382'><span style='mso-bidi-font-size:\n" +
            "  10.5pt;font-family:宋体'>数 量<span lang=EN-US><o:p></o:p></span></span></span></p>\n" +
            "  </td>\n" +
            "  <span style='mso-bookmark:_Hlk51011382'></span>\n" +
            "  <td width=84 style='width:62.75pt;border-top:double windowtext 1.5pt;\n" +
            "  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-left-alt:solid windowtext .75pt;mso-border-alt:solid windowtext .75pt;\n" +
            "  mso-border-top-alt:double windowtext 1.5pt;padding:0cm 5.4pt 0cm 5.4pt;\n" +
            "  height:34.0pt'>\n" +
            "  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:\n" +
            "  char'><span style='mso-bookmark:_Hlk51011382'><span style='mso-bidi-font-size:\n" +
            "  10.5pt;font-family:宋体'>性 状<span lang=EN-US><o:p></o:p></span></span></span></p>\n" +
            "  </td>\n" +
            "  <span style='mso-bookmark:_Hlk51011382'></span>\n" +
            "  <td width=84 style='width:62.7pt;border-top:double windowtext 1.5pt;\n" +
            "  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-left-alt:solid windowtext .75pt;mso-border-alt:solid windowtext .75pt;\n" +
            "  mso-border-top-alt:double windowtext 1.5pt;padding:0cm 5.4pt 0cm 5.4pt;\n" +
            "  height:34.0pt'>\n" +
            "  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:\n" +
            "  char'><span style='mso-bookmark:_Hlk51011382'><span style='mso-bidi-font-size:\n" +
            "  10.5pt;font-family:宋体'>包装情况<span lang=EN-US><o:p></o:p></span></span></span></p>\n" +
            "  </td>\n" +
            "  <span style='mso-bookmark:_Hlk51011382'></span>\n" +
            "  <td width=149 style='width:111.55pt;border-top:double windowtext 1.5pt;\n" +
            "  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-left-alt:solid windowtext .75pt;mso-border-alt:solid windowtext .75pt;\n" +
            "  mso-border-top-alt:double windowtext 1.5pt;padding:0cm 5.4pt 0cm 5.4pt;\n" +
            "  height:34.0pt'>\n" +
            "  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:\n" +
            "  char'><span style='mso-bookmark:_Hlk51011382'><span style='mso-bidi-font-size:\n" +
            "  10.5pt;font-family:宋体'>提取部位<span lang=EN-US><o:p></o:p></span></span></span></p>\n" +
            "  </td>\n" +
            "  <span style='mso-bookmark:_Hlk51011382'></span>\n" +
            "  <td width=110 style='width:82.45pt;border-top:double windowtext 1.5pt;\n" +
            "  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:double windowtext 1.5pt;\n" +
            "  mso-border-left-alt:solid windowtext .75pt;mso-border-top-alt:double 1.5pt;\n" +
            "  mso-border-left-alt:solid .75pt;mso-border-bottom-alt:solid .75pt;mso-border-right-alt:\n" +
            "  double 1.5pt;mso-border-color-alt:windowtext;padding:0cm 5.4pt 0cm 5.4pt;\n" +
            "  height:34.0pt'>\n" +
            "  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:\n" +
            "  char'><span style='mso-bookmark:_Hlk51011382'><span style='mso-bidi-font-size:\n" +
            "  10.5pt;font-family:宋体'>提取方法<span lang=EN-US><o:p></o:p></span></span></span></p>\n" +
            "  </td>\n" +
            "  <span style='mso-bookmark:_Hlk51011382'></span>\n" +
            " </tr>";

    static String jcTr = "<tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes;mso-yfti-lastrow:yes;\n" +
            "  page-break-inside:avoid;height:34.0pt'>\n" +
            "  <td width=35 style='width:26.25pt;border:double windowtext 1.5pt;border-right:\n" +
            "  solid windowtext 1.0pt;mso-border-alt:double windowtext 1.5pt;mso-border-right-alt:\n" +
            "  solid windowtext .75pt;padding:0cm 5.4pt 0cm 5.4pt;height:34.0pt'>\n" +
            "  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:\n" +
            "  char'><span class=SpellE><span lang=EN-US style='mso-bidi-font-size:10.5pt;\n" +
            "  font-family:宋体'>jcxh</span></span><span lang=EN-US style='mso-bidi-font-size:\n" +
            "  10.5pt;font-family:宋体'><o:p></o:p></span></p>\n" +
            "  </td>\n" +
            "  <td width=131 style='width:98.35pt;border-top:double windowtext 1.5pt;\n" +
            "  border-left:none;border-bottom:double windowtext 1.5pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-left-alt:solid windowtext .75pt;mso-border-top-alt:double 1.5pt;\n" +
            "  mso-border-left-alt:solid .75pt;mso-border-bottom-alt:double 1.5pt;\n" +
            "  mso-border-right-alt:solid .75pt;mso-border-color-alt:windowtext;padding:\n" +
            "  0cm 5.4pt 0cm 5.4pt;height:34.0pt'>\n" +
            "  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:\n" +
            "  char'><span class=SpellE><span lang=EN-US style='mso-bidi-font-size:10.5pt;\n" +
            "  font-family:仿宋_GB2312;mso-hansi-font-family:宋体'>jcname</span></span><span\n" +
            "  lang=EN-US style='mso-bidi-font-size:10.5pt;font-family:仿宋_GB2312;mso-hansi-font-family:\n" +
            "  宋体'><o:p></o:p></span></p>\n" +
            "  </td>\n" +
            "  <td width=56 style='width:41.75pt;border-top:double windowtext 1.5pt;\n" +
            "  border-left:none;border-bottom:double windowtext 1.5pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-left-alt:solid windowtext .75pt;mso-border-top-alt:double 1.5pt;\n" +
            "  mso-border-left-alt:solid .75pt;mso-border-bottom-alt:double 1.5pt;\n" +
            "  mso-border-right-alt:solid .75pt;mso-border-color-alt:windowtext;padding:\n" +
            "  0cm 5.4pt 0cm 5.4pt;height:34.0pt'>\n" +
            "  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:\n" +
            "  char'><span class=SpellE><span lang=EN-US style='mso-bidi-font-size:10.5pt;\n" +
            "  font-family:仿宋_GB2312;mso-hansi-font-family:宋体'>jcnum</span></span><span\n" +
            "  lang=EN-US style='mso-bidi-font-size:10.5pt;font-family:仿宋_GB2312;mso-hansi-font-family:\n" +
            "  宋体'><o:p></o:p></span></p>\n" +
            "  </td>\n" +
            "  <td width=84 style='width:62.75pt;border-top:double windowtext 1.5pt;\n" +
            "  border-left:none;border-bottom:double windowtext 1.5pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-left-alt:solid windowtext .75pt;mso-border-top-alt:double 1.5pt;\n" +
            "  mso-border-left-alt:solid .75pt;mso-border-bottom-alt:double 1.5pt;\n" +
            "  mso-border-right-alt:solid .75pt;mso-border-color-alt:windowtext;padding:\n" +
            "  0cm 5.4pt 0cm 5.4pt;height:34.0pt'>\n" +
            "  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:\n" +
            "  char'><span class=SpellE><span lang=EN-US style='mso-bidi-font-size:10.5pt;\n" +
            "  font-family:仿宋_GB2312;mso-hansi-font-family:宋体'>jcxz</span></span><span\n" +
            "  lang=EN-US style='mso-bidi-font-size:10.5pt;font-family:仿宋_GB2312;mso-hansi-font-family:\n" +
            "  宋体'><o:p></o:p></span></p>\n" +
            "  </td>\n" +
            "  <td width=84 style='width:62.7pt;border-top:double windowtext 1.5pt;\n" +
            "  border-left:none;border-bottom:double windowtext 1.5pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-left-alt:solid windowtext .75pt;mso-border-top-alt:double 1.5pt;\n" +
            "  mso-border-left-alt:solid .75pt;mso-border-bottom-alt:double 1.5pt;\n" +
            "  mso-border-right-alt:solid .75pt;mso-border-color-alt:windowtext;padding:\n" +
            "  0cm 5.4pt 0cm 5.4pt;height:34.0pt'>\n" +
            "  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:\n" +
            "  char'><span class=SpellE><span lang=EN-US style='mso-bidi-font-size:10.5pt;\n" +
            "  font-family:仿宋_GB2312;mso-hansi-font-family:宋体'>jcbzqk</span></span><span\n" +
            "  lang=EN-US style='mso-bidi-font-size:10.5pt;font-family:仿宋_GB2312;mso-hansi-font-family:\n" +
            "  宋体'><o:p></o:p></span></p>\n" +
            "  </td>\n" +
            "  <td width=149 style='width:111.55pt;border-top:double windowtext 1.5pt;\n" +
            "  border-left:none;border-bottom:double windowtext 1.5pt;border-right:solid windowtext 1.0pt;\n" +
            "  mso-border-left-alt:solid windowtext .75pt;mso-border-top-alt:double 1.5pt;\n" +
            "  mso-border-left-alt:solid .75pt;mso-border-bottom-alt:double 1.5pt;\n" +
            "  mso-border-right-alt:solid .75pt;mso-border-color-alt:windowtext;padding:\n" +
            "  0cm 5.4pt 0cm 5.4pt;height:34.0pt'>\n" +
            "  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:\n" +
            "  char'><span class=SpellE><span lang=EN-US style='mso-bidi-font-size:10.5pt;\n" +
            "  font-family:仿宋_GB2312;mso-hansi-font-family:宋体'>jctqbw</span></span><span\n" +
            "  lang=EN-US style='mso-bidi-font-size:10.5pt;font-family:仿宋_GB2312;mso-hansi-font-family:\n" +
            "  宋体'><o:p></o:p></span></p>\n" +
            "  </td>\n" +
            "  <td width=110 style='width:82.45pt;border:double windowtext 1.5pt;border-left:\n" +
            "  none;mso-border-left-alt:solid windowtext .75pt;padding:0cm 5.4pt 0cm 5.4pt;\n" +
            "  height:34.0pt'>\n" +
            "  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:\n" +
            "  char'><span class=SpellE><span lang=EN-US style='mso-bidi-font-size:10.5pt;\n" +
            "  font-family:仿宋_GB2312;mso-hansi-font-family:宋体'>jctqfa</span></span><span\n" +
            "  lang=EN-US style='mso-bidi-font-size:10.5pt;font-family:仿宋_GB2312;mso-hansi-font-family:\n" +
            "  宋体'><o:p></o:p></span></p>\n" +
            "  </td>\n" +
            " </tr>";

    static String jcFoot = "<tr style='mso-yfti-irow:2;mso-yfti-lastrow:yes;page-break-inside:avoid;\n" +
            "  height:34.0pt'>\n" +
            "  <td width=166 colspan=2 style='width:124.6pt;border-top:none;border-left:\n" +
            "  double windowtext 1.5pt;border-bottom:double windowtext 1.5pt;border-right:\n" +
            "  solid windowtext 1.0pt;mso-border-top-alt:solid windowtext .75pt;mso-border-top-alt:\n" +
            "  solid .75pt;mso-border-left-alt:double 1.5pt;mso-border-bottom-alt:double 1.5pt;\n" +
            "  mso-border-right-alt:solid .75pt;mso-border-color-alt:windowtext;padding:\n" +
            "  0cm 5.4pt 0cm 5.4pt;height:34.0pt'>\n" +
            "  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:\n" +
            "  char'><span style='mso-bookmark:_Hlk51011382'><span style='mso-bidi-font-size:\n" +
            "  10.5pt;font-family:宋体'>送检人签字<span lang=EN-US><o:p></o:p></span></span></span></p>\n" +
            "  </td>\n" +
            "  <span style='mso-bookmark:_Hlk51011382'></span>\n" +
            "  <td width=482 colspan=5 style='width:361.2pt;border-top:none;border-left:\n" +
            "  none;border-bottom:double windowtext 1.5pt;border-right:double windowtext 1.5pt;\n" +
            "  mso-border-top-alt:solid windowtext .75pt;mso-border-left-alt:solid windowtext .75pt;\n" +
            "  padding:0cm 5.4pt 0cm 5.4pt;height:34.0pt'>\n" +
            "  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:\n" +
            "  char'><span style='mso-bookmark:_Hlk51011382'><span lang=EN-US\n" +
            "  style='font-family:仿宋_GB2312;mso-hansi-font-family:??;mso-bidi-font-family:\n" +
            "  ??;color:windowtext'>jcsjrqz</span></span><span style='mso-bookmark:_Hlk51011382'><span\n" +
            "  lang=EN-US style='mso-bidi-font-size:10.5pt;font-family:宋体'><o:p></o:p></span></span></p>\n" +
            "  </td>\n" +
            "  <span style='mso-bookmark:_Hlk51011382'></span>\n" +
            " </tr>\n" +
            "</table>";
};

