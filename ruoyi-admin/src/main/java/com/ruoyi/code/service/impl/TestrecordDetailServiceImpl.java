package com.ruoyi.code.service.impl;

import com.ruoyi.code.domain.TestrecordDetail;
import com.ruoyi.code.mapper.TestrecordDetailMapper;
import com.ruoyi.code.service.ITestrecordDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 鉴定详情Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-18
 */
@Service
public class TestrecordDetailServiceImpl implements ITestrecordDetailService 
{
    @Autowired
    private TestrecordDetailMapper testrecordDetailMapper;

    /**
     * 查询鉴定详情
     * 
     * @param sampleId 鉴定详情ID
     * @return 鉴定详情
     */
    @Override
    public TestrecordDetail selectTestrecordDetailBySampleId(String sampleId)
    {
        return testrecordDetailMapper.selectTestrecordDetailBySampleId(sampleId);
    }

    /**
     * 查询鉴定详情列表
     * 
     * @param testrecordDetail 鉴定详情
     * @return 鉴定详情
     */
    @Override
    public List<TestrecordDetail> selectTestrecordDetailList(TestrecordDetail testrecordDetail)
    {
        return testrecordDetailMapper.selectTestrecordDetailList(testrecordDetail);
    }

    /**
     * 新增鉴定详情
     * 
     * @param testrecordDetail 鉴定详情
     * @return 结果
     */
    @Override
    public int insertTestrecordDetail(TestrecordDetail testrecordDetail)
    {
        return testrecordDetailMapper.insertTestrecordDetail(testrecordDetail);
    }

    /**
     * 保存鉴定详情(多条)
     */
    @Override
    public int doSaveTestrecordDetail(List<TestrecordDetail> list){
        try {
            int a = testrecordDetailMapper.deleteTestrecordDetailByTrustId(list.get(0).getTrustId());
            if(a>-1){
                TestrecordDetail testrecordDetail = new TestrecordDetail();
                int b = -1;
                for(int i=0;i<list.size();i++){
                    testrecordDetail = list.get(i);
                    b = testrecordDetailMapper.insertTestrecordDetail(testrecordDetail);
                    if(b<=0){
                        throw new RuntimeException();
                    }
                }
                return 1;
            }else{
                return -1;
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    };
}
