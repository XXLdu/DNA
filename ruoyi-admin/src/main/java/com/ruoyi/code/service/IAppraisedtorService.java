package com.ruoyi.code.service;

import java.util.List;
import com.ruoyi.code.domain.Appraisedtor;

/**
 * 被鉴定人Service接口
 * 
 * @author ruoyi
 * @date 2020-05-31
 */
public interface IAppraisedtorService 
{
    /**
     * 查询被鉴定人
     * 
     * @param id 被鉴定人ID
     * @return 被鉴定人
     */
    public Appraisedtor selectAppraisedtorById(String id);

    /**
     * 查询被鉴定人列表
     * 
     * @param appraisedtor 被鉴定人
     * @return 被鉴定人集合
     */
    public List<Appraisedtor> selectAppraisedtorList(Appraisedtor appraisedtor);

    /**
     * 新增被鉴定人
     * 
     * @param appraisedtor 被鉴定人
     * @return 结果
     */
    public int insertAppraisedtor(Appraisedtor appraisedtor);

    /**
     * 修改被鉴定人
     * 
     * @param appraisedtor 被鉴定人
     * @return 结果
     */
    public int updateAppraisedtor(Appraisedtor appraisedtor);

    /**
     * 批量删除被鉴定人
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAppraisedtorByIds(String ids);

    /**
     * 删除被鉴定人信息
     * 
     * @param id 被鉴定人ID
     * @return 结果
     */
    public int deleteAppraisedtorById(String id);
}
