package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysUserPost;

/**
 * 用户与职务关联表 数据层
 * 
 * @author ruoyi
 */
public interface SysUserPostMapper
{
    /**
     * 通过用户ID删除用户和职务关联
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserPostByUserId(Long userId);
    
    /**
     * 通过职务ID查询职务使用数量
     * 
     * @param postId 职务ID
     * @return 结果
     */
    public int countUserPostById(Long postId);
    
    /**
     * 批量删除用户和职务关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserPost(Long[] ids);

    /**
     * 批量新增用户职务信息
     * 
     * @param userPostList 用户角色列表
     * @return 结果
     */
    public int batchUserPost(List<SysUserPost> userPostList);
}
