package com.ruoyi.code.mapper;

import java.util.List;
import com.ruoyi.code.domain.Physical;

/**
 * 物证Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-31
 */
public interface PhysicalMapper 
{
    /**
     * 查询物证
     * 
     * @param id 物证ID
     * @return 物证
     */
    public Physical selectPhysicalById(String id);

    /**
     * 查询物证列表
     * 
     * @param physical 物证
     * @return 物证集合
     */
    public List<Physical> selectPhysicalList(Physical physical);

    /**
     * 新增物证
     * 
     * @param physical 物证
     * @return 结果
     */
    public int insertPhysical(Physical physical);

    /**
     * 修改物证
     * 
     * @param physical 物证
     * @return 结果
     */
    public int updatePhysical(Physical physical);

    /**
     * 删除物证
     * 
     * @param id 物证ID
     * @return 结果
     */
    public int deletePhysicalById(String id);

    /**
     * 批量删除物证
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePhysicalByIds(String[] ids);
}
