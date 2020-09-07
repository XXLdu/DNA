package com.ruoyi.code.service.impl;

import com.ruoyi.code.domain.Sample;
import com.ruoyi.code.mapper.PhysicalDeliverMapper;
import com.ruoyi.code.mapper.SampleMapper;
import com.ruoyi.code.service.ISampleService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 检材Service业务层处理
 * 
 * @author dqs
 * @date 2020-07-08
 */
@Service
public class SampleServiceImpl implements ISampleService 
{
    @Autowired
    private SampleMapper sampleMapper;

    @Autowired
    private SampleServiceImpl sampleServiceImpl;

    @Autowired
    private TestrecordServiceImpl testrecordServiceImpl;

    @Autowired
    private PhysicalDeliverMapper physicalDeliverMapper;

    @Autowired
    private SysUserServiceImpl sysUserServiceImpl;
    /**
     * 查询检材
     * 
     * @param id 检材ID
     * @return 检材
     */
    @Override
    public Sample selectSampleById(String id)
    {
        return sampleMapper.selectSampleById(id);
    }

    /**
     * 查询检材列表
     * 
     * @param sample 检材
     * @return 检材
     */
    @Override
    public List<Sample> selectSampleList(Sample sample)
    {
        return sampleMapper.selectSampleList(sample);
    }

    /**
     * 新增检材
     * 
     * @param sample 检材
     * @return 结果
     */
    @Override
    public int insertSample(Sample sample)
    {
        return sampleMapper.insertSample(sample);
    }

    /**
     * 修改检材
     * 
     * @param sample 检材
     * @return 结果
     */
    @Override
    public int updateSample(Sample sample)
    {
        return sampleMapper.updateSample(sample);
    }

    /**
     * 删除检材对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSampleByIds(String ids)
    {
        return sampleMapper.deleteSampleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除检材信息
     * 
     * @param id 检材ID
     * @return 结果
     */
    @Override
    public int deleteSampleById(String id)
    {
        return sampleMapper.deleteSampleById(id);
    }


    /**
     * 查询检材列表
     *
     * @param
     * @return 检材
     */
    @Override
    public List<Sample> selectSampleListByProcessCode()
    {
        return sampleMapper.selectSampleListByProcessCode();
    }

}
