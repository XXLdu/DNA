package com.ruoyi.web.controller.system;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.service.ISysPostService;

/**
 * 职务信息操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/post")
public class SysPostController extends BaseController
{
    private String prefix = "system/post";

    @Autowired
    private ISysPostService postService;

    @RequiresPermissions("system:post:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/post";
    }

    @RequiresPermissions("system:post:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysPost post)
    {
        startPage();
        List<SysPost> list = postService.selectPostList(post);
        return getDataTable(list);
    }

    @Log(title = "职务管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:post:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysPost post)
    {
        List<SysPost> list = postService.selectPostList(post);
        ExcelUtil<SysPost> util = new ExcelUtil<SysPost>(SysPost.class);
        return util.exportExcel(list, "职务数据");
    }

    @RequiresPermissions("system:post:remove")
    @Log(title = "职务管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        try
        {
            return toAjax(postService.deletePostByIds(ids));
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }

    /**
     * 新增职务
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存职务
     */
    @RequiresPermissions("system:post:add")
    @Log(title = "职务管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysPost post)
    {
        if (UserConstants.POST_NAME_NOT_UNIQUE.equals(postService.checkPostNameUnique(post)))
        {
            return error("新增职务'" + post.getPostName() + "'失败，职务名称已存在");
        }
        else if (UserConstants.POST_CODE_NOT_UNIQUE.equals(postService.checkPostCodeUnique(post)))
        {
            return error("新增职务'" + post.getPostName() + "'失败，职务编码已存在");
        }
        post.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(postService.insertPost(post));
    }

    /**
     * 修改职务
     */
    @GetMapping("/edit/{postId}")
    public String edit(@PathVariable("postId") Long postId, ModelMap mmap)
    {
        mmap.put("post", postService.selectPostById(postId));
        return prefix + "/edit";
    }

    /**
     * 修改保存职务
     */
    @RequiresPermissions("system:post:edit")
    @Log(title = "职务管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysPost post)
    {
        if (UserConstants.POST_NAME_NOT_UNIQUE.equals(postService.checkPostNameUnique(post)))
        {
            return error("修改职务'" + post.getPostName() + "'失败，职务名称已存在");
        }
        else if (UserConstants.POST_CODE_NOT_UNIQUE.equals(postService.checkPostCodeUnique(post)))
        {
            return error("修改职务'" + post.getPostName() + "'失败，职务编码已存在");
        }
        post.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(postService.updatePost(post));
    }

    /**
     * 校验职务名称
     */
    @PostMapping("/checkPostNameUnique")
    @ResponseBody
    public String checkPostNameUnique(SysPost post)
    {
        return postService.checkPostNameUnique(post);
    }

    /**
     * 校验职务编码
     */
    @PostMapping("/checkPostCodeUnique")
    @ResponseBody
    public String checkPostCodeUnique(SysPost post)
    {
        return postService.checkPostCodeUnique(post);
    }
}
