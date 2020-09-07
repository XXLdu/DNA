package com.ruoyi.code.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.code.mapper.TestrecordMapper;
import com.ruoyi.code.domain.Testrecord;
import com.ruoyi.code.service.ITestrecordService;
import com.ruoyi.common.core.text.Convert;

/**
 * 检验记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
@Service
public class TestrecordServiceImpl implements ITestrecordService 
{
    @Autowired
    private TestrecordMapper testrecordMapper;

    /**
     * 查询检验记录
     * 
     * @param id 检验记录ID
     * @return 检验记录
     */
    @Override
    public Testrecord selectTestrecordById(String id)
    {
        return testrecordMapper.selectTestrecordById(id);
    }

    /**
     * 查询检验记录
     *
     * @param trustId
     * @return 检验记录
     */
    @Override
    public Testrecord selectTestrecordByTrustId(String trustId)
    {
        return testrecordMapper.selectTestrecordByTrustId(trustId);
    }
    /**
     * 查询检验记录列表
     * 
     * @param testrecord 检验记录
     * @return 检验记录
     */
    @Override
    public List<Testrecord> selectTestrecordList(Testrecord testrecord)
    {
        return testrecordMapper.selectTestrecordList(testrecord);
    }

    /**
     * 新增检验记录
     * 
     * @param testrecord 检验记录
     * @return 结果
     */
    @Override
    public int insertTestrecord(Testrecord testrecord)
    {
        return testrecordMapper.insertTestrecord(testrecord);
    }

    /**
     * 修改检验记录
     * 
     * @param testrecord 检验记录
     * @return 结果
     */
    @Override
    public int updateTestrecord(Testrecord testrecord)
    {
        return testrecordMapper.updateTestrecord(testrecord);
    }

    /**
     * 删除检验记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTestrecordByIds(String ids)
    {
        return testrecordMapper.deleteTestrecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除检验记录信息
     * 
     * @param id 检验记录ID
     * @return 结果
     */
    @Override
    public int deleteTestrecordById(String id)
    {
        return testrecordMapper.deleteTestrecordById(id);
    }
}
