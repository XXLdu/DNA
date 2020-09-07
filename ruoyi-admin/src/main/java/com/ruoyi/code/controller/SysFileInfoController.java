package com.ruoyi.code.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.file.FileUploadUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.code.domain.SysFileInfo;
import com.ruoyi.code.service.ISysFileInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件信息Controller
 *
 * @author ruoyi
 * @date 2020-07-08
 */
@Controller
@RequestMapping("/code/info")
public class SysFileInfoController extends BaseController
{
    private String prefix = "code/info";

    @Autowired
    private ISysFileInfoService sysFileInfoService;

    @RequiresPermissions("code:info:view")
    @GetMapping()
    public String info()
    {
        return prefix + "/info";
    }

    /**
     * 查询文件信息列表
     */
    @RequiresPermissions("code:info:list")
    @PostMapping("/list/{fatherId}")
    @ResponseBody
    public TableDataInfo list(@PathVariable("fatherId") String fatherId,SysFileInfo sysFileInfo)
    {
        startPage();
        sysFileInfo.setFatherId(fatherId);
        List<SysFileInfo> list = sysFileInfoService.selectSysFileInfoList(sysFileInfo);
        return getDataTable(list);
    }

    /**
     * 导出文件信息列表
     */
    @RequiresPermissions("code:info:export")
    @Log(title = "文件信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysFileInfo sysFileInfo)
    {
        List<SysFileInfo> list = sysFileInfoService.selectSysFileInfoList(sysFileInfo);
        ExcelUtil<SysFileInfo> util = new ExcelUtil<SysFileInfo>(SysFileInfo.class);
        return util.exportExcel(list, "info");
    }

    /**
     * 新增文件信息
     */
    @PostMapping("/add/{fatherId}")
    @ResponseBody
    public AjaxResult addSave(@PathVariable("fatherId") String fatherId, @RequestParam("fileinput") MultipartFile file, SysFileInfo sysFileInfo) throws IOException
    {
        // 上传文件路径
        String filePath = Global.getUploadPath();
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        // 上传并返回新文件名称
        String filePath_name = FileUploadUtils.upload(filePath, file);
        sysFileInfo.setFileId(uuid);
        sysFileInfo.setFatherId(fatherId);
        sysFileInfo.setFilePath(filePath_name);
        sysFileInfo.setFileName(file.getOriginalFilename());
        return toAjax(sysFileInfoService.insertSysFileInfo(sysFileInfo));
    }

    /**
     * 删除文件信息
     */
    @RequiresPermissions("code:info:remove")
    @Log(title = "文件信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysFileInfoService.deleteSysFileInfoByIds(ids));
    }

    /**
     * 获取页面回显文件信息
     */
    @RequiresPermissions("code:info:getFiledata")
    @Log(title = "文件信息", businessType = BusinessType.DELETE)
    @PostMapping( "/getFiledata")
    @ResponseBody
    public String getFiledata(String id)
    {
        return JSON.toJSONString(sysFileInfoService.getFileData(id));
    }

}
