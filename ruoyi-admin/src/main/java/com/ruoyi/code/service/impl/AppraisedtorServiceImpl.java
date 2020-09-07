package com.ruoyi.code.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.code.mapper.AppraisedtorMapper;
import com.ruoyi.code.domain.Appraisedtor;
import com.ruoyi.code.service.IAppraisedtorService;
import com.ruoyi.common.core.text.Convert;

/**
 * 被鉴定人Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-31
 */
@Service
public class AppraisedtorServiceImpl implements IAppraisedtorService 
{
    @Autowired
    private AppraisedtorMapper appraisedtorMapper;

    /**
     * 查询被鉴定人
     * 
     * @param id 被鉴定人ID
     * @return 被鉴定人
     */
    @Override
    public Appraisedtor selectAppraisedtorById(String id)
    {
        return appraisedtorMapper.selectAppraisedtorById(id);
    }

    /**
     * 查询被鉴定人列表
     * 
     * @param appraisedtor 被鉴定人
     * @return 被鉴定人
     */
    @Override
    public List<Appraisedtor> selectAppraisedtorList(Appraisedtor appraisedtor)
    {
        return appraisedtorMapper.selectAppraisedtorList(appraisedtor);
    }

    /**
     * 新增被鉴定人
     * 
     * @param appraisedtor 被鉴定人
     * @return 结果
     */
    @Override
    public int insertAppraisedtor(Appraisedtor appraisedtor)
    {
        return appraisedtorMapper.insertAppraisedtor(appraisedtor);
    }

    /**
     * 修改被鉴定人
     * 
     * @param appraisedtor 被鉴定人
     * @return 结果
     */
    @Override
    public int updateAppraisedtor(Appraisedtor appraisedtor)
    {
        return appraisedtorMapper.updateAppraisedtor(appraisedtor);
    }

    /**
     * 删除被鉴定人对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAppraisedtorByIds(String ids)
    {
        return appraisedtorMapper.deleteAppraisedtorByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除被鉴定人信息
     * 
     * @param id 被鉴定人ID
     * @return 结果
     */
    @Override
    public int deleteAppraisedtorById(String id)
    {
        return appraisedtorMapper.deleteAppraisedtorById(id);
    }
}
