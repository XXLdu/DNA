package com.ruoyi.code.service;

import java.util.List;
import com.ruoyi.code.domain.TestrecordDetail;

/**
 * 鉴定详情Service接口
 * 
 * @author ruoyi
 * @date 2020-07-18
 */
public interface ITestrecordDetailService 
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
     * 保存鉴定详情(多条)
     */
    public int doSaveTestrecordDetail(List<TestrecordDetail> list);
}
