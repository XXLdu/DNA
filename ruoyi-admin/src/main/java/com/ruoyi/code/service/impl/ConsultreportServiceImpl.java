package com.ruoyi.code.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.code.mapper.ConsultreportMapper;
import com.ruoyi.code.domain.Consultreport;
import com.ruoyi.code.service.IConsultreportService;
import com.ruoyi.common.core.text.Convert;

/**
 * 会诊报告（CONSULTREPORT）Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
@Service
public class ConsultreportServiceImpl implements IConsultreportService 
{
    @Autowired
    private ConsultreportMapper consultreportMapper;

    /**
     * 查询会诊报告（CONSULTREPORT）
     * 
     * @param id 会诊报告（CONSULTREPORT）ID
     * @return 会诊报告（CONSULTREPORT）
     */
    @Override
    public Consultreport selectConsultreportById(String id)
    {
        return consultreportMapper.selectConsultreportById(id);
    }

    /**
     * 查询会诊报告（CONSULTREPORT）列表
     * 
     * @param consultreport 会诊报告（CONSULTREPORT）
     * @return 会诊报告（CONSULTREPORT）
     */
    @Override
    public List<Consultreport> selectConsultreportList(Consultreport consultreport)
    {
        return consultreportMapper.selectConsultreportList(consultreport);
    }

    /**
     * 新增会诊报告（CONSULTREPORT）
     * 
     * @param consultreport 会诊报告（CONSULTREPORT）
     * @return 结果
     */
    @Override
    public int insertConsultreport(Consultreport consultreport)
    {
        return consultreportMapper.insertConsultreport(consultreport);
    }

    /**
     * 修改会诊报告（CONSULTREPORT）
     * 
     * @param consultreport 会诊报告（CONSULTREPORT）
     * @return 结果
     */
    @Override
    public int updateConsultreport(Consultreport consultreport)
    {
        return consultreportMapper.updateConsultreport(consultreport);
    }

    /**
     * 删除会诊报告（CONSULTREPORT）对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConsultreportByIds(String ids)
    {
        return consultreportMapper.deleteConsultreportByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除会诊报告（CONSULTREPORT）信息
     * 
     * @param id 会诊报告（CONSULTREPORT）ID
     * @return 结果
     */
    @Override
    public int deleteConsultreportById(String id)
    {
        return consultreportMapper.deleteConsultreportById(id);
    }
}
