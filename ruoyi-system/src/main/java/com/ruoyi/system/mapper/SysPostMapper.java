package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysPost;

/**
 * 职务信息 数据层
 * 
 * @author ruoyi
 */
public interface SysPostMapper
{
    /**
     * 查询职务数据集合
     * 
     * @param post 职务信息
     * @return 职务数据集合
     */
    public List<SysPost> selectPostList(SysPost post);

    /**
     * 查询所有职务
     * 
     * @return 职务列表
     */
    public List<SysPost> selectPostAll();

    /**
     * 根据用户ID查询职务
     * 
     * @param userId 用户ID
     * @return 职务列表
     */
    public List<SysPost> selectPostsByUserId(Long userId);

    /**
     * 通过职务ID查询职务信息
     * 
     * @param postId 职务ID
     * @return 角色对象信息
     */
    public SysPost selectPostById(Long postId);

    /**
     * 批量删除职务信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePostByIds(Long[] ids);

    /**
     * 修改职务信息
     * 
     * @param post 职务信息
     * @return 结果
     */
    public int updatePost(SysPost post);

    /**
     * 新增职务信息
     * 
     * @param post 职务信息
     * @return 结果
     */
    public int insertPost(SysPost post);

    /**
     * 校验职务名称
     * 
     * @param postName 职务名称
     * @return 结果
     */
    public SysPost checkPostNameUnique(String postName);

    /**
     * 校验职务编码
     * 
     * @param postCode 职务编码
     * @return 结果
     */
    public SysPost checkPostCodeUnique(String postCode);
}
