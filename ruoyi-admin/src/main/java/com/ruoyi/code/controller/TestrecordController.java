package com.ruoyi.code.controller;

import com.ruoyi.code.domain.Testrecord;
import com.ruoyi.code.service.ITestrecordService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 检验记录Controller
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
@Controller
@RequestMapping("/code/testrecord")
public class TestrecordController extends BaseController
{
    private String prefix = "code/testrecord";

    @Autowired
    private ITestrecordService testrecordService;

    @Autowired
    private ISysUserService userService;

    @RequiresPermissions("code:testrecord:view")
    @GetMapping("/{trust_id}")
    public String testrecord(@PathVariable("trust_id") String trust_id, ModelMap mmap)
    {
        mmap.put("trust_id", trust_id);
        return prefix + "/testrecord";
    }

    /**
     * 查询检验记录列表
     */
    @RequiresPermissions("code:testrecord:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Testrecord testrecord)
    {
        startPage();
        List<Testrecord> list = testrecordService.selectTestrecordList(testrecord);
        return getDataTable(list);
    }

    /**
     * 导出检验记录列表
     */
    @RequiresPermissions("code:testrecord:export")
    @Log(title = "检验记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Testrecord testrecord)
    {
        List<Testrecord> list = testrecordService.selectTestrecordList(testrecord);
        ExcelUtil<Testrecord> util = new ExcelUtil<Testrecord>(Testrecord.class);
        return util.exportExcel(list, "testrecord");
    }

    /**
     * 新增检验记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存检验记录
     */
    @RequiresPermissions("code:testrecord:add")
    @Log(title = "检验记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Testrecord testrecord)
    {
        return toAjax(testrecordService.insertTestrecord(testrecord));
    }

    /**
     * 修改检验记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        Testrecord testrecord = testrecordService.selectTestrecordById(id);
        mmap.put("testrecord", testrecord);
        return prefix + "/edit";
    }

    /**
     * 修改检验记录
     */
    @GetMapping("/editByTrustId/{trustId}")
    public String editByTrustId(@PathVariable("trustId") String trustId, ModelMap mmap)
    {
        Testrecord testrecord = testrecordService.selectTestrecordByTrustId(trustId);
        SysUser user = new SysUser();
        user.setRoleId(new Long(100));
        List<SysUser> fyUserList = userService.selectUserList(user);
        mmap.put("fyUserList", fyUserList);
        user.setRoleId(new Long(101));
        List<SysUser> reviewerUserList = userService.selectUserList(user);
        mmap.put("reviewerUserList", reviewerUserList);

        if(testrecord != null){
            mmap.put("testrecord", testrecord);
            return prefix + "/edit";
        }else{
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            mmap.put("trustId", trustId);
            mmap.put("id", uuid);
            return prefix + "/add";
        }
    }

    /**
     * 修改保存检验记录
     */
    @RequiresPermissions("code:testrecord:edit")
    @Log(title = "检验记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Testrecord testrecord)
    {
        return toAjax(testrecordService.updateTestrecord(testrecord));
    }

    /**
     * 删除检验记录
     */
    @RequiresPermissions("code:testrecord:remove")
    @Log(title = "检验记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(testrecordService.deleteTestrecordByIds(ids));
    }
}
