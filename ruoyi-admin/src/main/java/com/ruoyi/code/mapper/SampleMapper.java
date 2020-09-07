package com.ruoyi.code.mapper;

import com.ruoyi.code.domain.Sample;

import java.util.List;

/**
 * 检材Mapper接口
 * 
 * @author dqs
 * @date 2020-07-08
 */
public interface SampleMapper 
{
    /**
     * 查询检材
     * 
     * @param id 检材ID
     * @return 检材
     */
    public Sample selectSampleById(String id);

    /**
     * 查询检材列表
     * 
     * @param sample 检材
     * @return 检材集合
     */
    public List<Sample> selectSampleList(Sample sample);

    /**
     * 新增检材
     * 
     * @param sample 检材
     * @return 结果
     */
    public int insertSample(Sample sample);

    /**
     * 修改检材
     * 
     * @param sample 检材
     * @return 结果
     */
    public int updateSample(Sample sample);

    /**
     * 删除检材
     * 
     * @param id 检材ID
     * @return 结果
     */
    public int deleteSampleById(String id);

    /**
     * 批量删除检材
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSampleByIds(String[] ids);


    /**
     * 查询检材列表
     *
     * @param
     * @return 检材集合
     */
    public List<Sample> selectSampleListByProcessCode();
}
