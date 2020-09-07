package com.ruoyi.code.mapper;

import java.util.List;
import com.ruoyi.code.domain.TestrecordDetail;

/**
 * 鉴定详情Mapper接口
 * 
 * @author ruoyi
 * @date 2020-07-18
 */
public interface TestrecordDetailMapper 
{
    /**
     * 查询鉴定详情
     * 
     * @param sampleId 鉴定详情ID
     * @return 鉴定详情
     */
    public TestrecordDetail selectTestrecordDetailBySampleId(String sampleId);

    /**
     * 查询鉴定详情列表
     * 
     * @param testrecordDetail 鉴定详情
     * @return 鉴定详情集合
     */
    public List<TestrecordDetail> selectTestrecordDetailList(TestrecordDetail testrecordDetail);

    /**
     * 新增鉴定详情
     * 
     * @param testrecordDetail 鉴定详情
     * @return 结果
     */
    public int insertTestrecordDetail(TestrecordDetail testrecordDetail);


    /**
     * 批量删除鉴定详情
     *
     * @param trustId 需要删除的数据ID
     * @return 结果
     */
    public int deleteTestrecordDetailByTrustId(String trustId);
}
