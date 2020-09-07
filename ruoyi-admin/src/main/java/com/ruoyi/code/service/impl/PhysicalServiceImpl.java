package com.ruoyi.code.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.code.mapper.PhysicalMapper;
import com.ruoyi.code.domain.Physical;
import com.ruoyi.code.service.IPhysicalService;
import com.ruoyi.common.core.text.Convert;

/**
 * 物证Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-31
 */
@Service
public class PhysicalServiceImpl implements IPhysicalService 
{
    @Autowired
    private PhysicalMapper physicalMapper;

    /**
     * 查询物证
     * 
     * @param id 物证ID
     * @return 物证
     */
    @Override
    public Physical selectPhysicalById(String id)
    {
        return physicalMapper.selectPhysicalById(id);
    }

    /**
     * 查询物证列表
     * 
     * @param physical 物证
     * @return 物证
     */
    @Override
    public List<Physical> selectPhysicalList(Physical physical)
    {
        return physicalMapper.selectPhysicalList(physical);
    }

    /**
     * 新增物证
     * 
     * @param physical 物证
     * @return 结果
     */
    @Override
    public int insertPhysical(Physical physical)
    {
        return physicalMapper.insertPhysical(physical);
    }

    /**
     * 修改物证
     * 
     * @param physical 物证
     * @return 结果
     */
    @Override
    public int updatePhysical(Physical physical)
    {
        return physicalMapper.updatePhysical(physical);
    }

    /**
     * 删除物证对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePhysicalByIds(String ids)
    {
        return physicalMapper.deletePhysicalByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除物证信息
     * 
     * @param id 物证ID
     * @return 结果
     */
    @Override
    public int deletePhysicalById(String id)
    {
        return physicalMapper.deletePhysicalById(id);
    }
}
