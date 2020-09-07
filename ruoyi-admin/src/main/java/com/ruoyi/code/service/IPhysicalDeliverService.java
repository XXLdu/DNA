package com.ruoyi.code.service;

import com.ruoyi.code.domain.PhysicalDeliver;
import com.ruoyi.code.domain.Trust;

import java.util.List;

/**
 * 物证流转Service接口
 * 
 * @author dqs
 * @date 2020-06-06
 */
public interface IPhysicalDeliverService 
{
    /**
     * 查询物证流转
     * 
     * @param id 物证流转ID
     * @return 物证流转
     */
    public PhysicalDeliver selectPhysicalDeliverById(String id);

    /**
     * 查询物证流转列表
     * 
     * @param physicalDeliver 物证流转
     * @return 物证流转集合
     */
    public List<PhysicalDeliver> selectPhysicalDeliverList(PhysicalDeliver physicalDeliver);

    /**
     * 新增物证流转
     * 
     * @param physicalDeliver 物证流转
     * @return 结果
     */
    public int insertPhysicalDeliver(PhysicalDeliver physicalDeliver);

    /**
     * 修改物证流转
     * 
     * @param physicalDeliver 物证流转
     * @return 结果
     */
    public int updatePhysicalDeliver(PhysicalDeliver physicalDeliver);

    /**
     * 批量删除物证流转
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePhysicalDeliverByIds(String ids);

    /**
     * 删除物证流转信息
     * 
     * @param id 物证流转ID
     * @return 结果
     */
    public int deletePhysicalDeliverById(String id);

    public void addPhysicalDeliver(Trust trust);
}
