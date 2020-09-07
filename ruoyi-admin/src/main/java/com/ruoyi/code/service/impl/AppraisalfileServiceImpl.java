package com.ruoyi.code.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.code.mapper.AppraisalfileMapper;
import com.ruoyi.code.domain.Appraisalfile;
import com.ruoyi.code.service.IAppraisalfileService;
import com.ruoyi.common.core.text.Convert;

/**
 * 鉴定文书（APPRAISALFILE）Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
@Service
public class AppraisalfileServiceImpl implements IAppraisalfileService 
{
    @Autowired
    private AppraisalfileMapper appraisalfileMapper;

    /**
     * 查询鉴定文书（APPRAISALFILE）
     * 
     * @param id 鉴定文书（APPRAISALFILE）ID
     * @return 鉴定文书（APPRAISALFILE）
     */
    @Override
    public Appraisalfile selectAppraisalfileById(String id)
    {
        return appraisalfileMapper.selectAppraisalfileById(id);
    }

    /**
     * 查询鉴定文书（APPRAISALFILE）列表
     * 
     * @param appraisalfile 鉴定文书（APPRAISALFILE）
     * @return 鉴定文书（APPRAISALFILE）
     */
    @Override
    public List<Appraisalfile> selectAppraisalfileList(Appraisalfile appraisalfile)
    {
        return appraisalfileMapper.selectAppraisalfileList(appraisalfile);
    }

    /**
     * 新增鉴定文书（APPRAISALFILE）
     * 
     * @param appraisalfile 鉴定文书（APPRAISALFILE）
     * @return 结果
     */
    @Override
    public int insertAppraisalfile(Appraisalfile appraisalfile)
    {
        return appraisalfileMapper.insertAppraisalfile(appraisalfile);
    }

    /**
     * 修改鉴定文书（APPRAISALFILE）
     * 
     * @param appraisalfile 鉴定文书（APPRAISALFILE）
     * @return 结果
     */
    @Override
    public int updateAppraisalfile(Appraisalfile appraisalfile)
    {
        return appraisalfileMapper.updateAppraisalfile(appraisalfile);
    }

    /**
     * 删除鉴定文书（APPRAISALFILE）对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAppraisalfileByIds(String ids)
    {
        return appraisalfileMapper.deleteAppraisalfileByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除鉴定文书（APPRAISALFILE）信息
     * 
     * @param id 鉴定文书（APPRAISALFILE）ID
     * @return 结果
     */
    @Override
    public int deleteAppraisalfileById(String id)
    {
        return appraisalfileMapper.deleteAppraisalfileById(id);
    }
}
