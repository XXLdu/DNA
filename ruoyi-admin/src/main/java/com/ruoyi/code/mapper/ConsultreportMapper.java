package com.ruoyi.code.mapper;

import java.util.List;
import com.ruoyi.code.domain.Consultreport;

/**
 * 会诊报告（CONSULTREPORT）Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
public interface ConsultreportMapper 
{
    /**
     * 查询会诊报告（CONSULTREPORT）
     * 
     * @param id 会诊报告（CONSULTREPORT）ID
     * @return 会诊报告（CONSULTREPORT）
     */
    public Consultreport selectConsultreportById(String id);

    /**
     * 查询会诊报告（CONSULTREPORT）列表
     * 
     * @param consultreport 会诊报告（CONSULTREPORT）
     * @return 会诊报告（CONSULTREPORT）集合
     */
    public List<Consultreport> selectConsultreportList(Consultreport consultreport);

    /**
     * 新增会诊报告（CONSULTREPORT）
     * 
     * @param consultreport 会诊报告（CONSULTREPORT）
     * @return 结果
     */
    public int insertConsultreport(Consultreport consultreport);

    /**
     * 修改会诊报告（CONSULTREPORT）
     * 
     * @param consultreport 会诊报告（CONSULTREPORT）
     * @return 结果
     */
    public int updateConsultreport(Consultreport consultreport);

    /**
     * 删除会诊报告（CONSULTREPORT）
     * 
     * @param id 会诊报告（CONSULTREPORT）ID
     * @return 结果
     */
    public int deleteConsultreportById(String id);

    /**
     * 批量删除会诊报告（CONSULTREPORT）
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConsultreportByIds(String[] ids);
}
