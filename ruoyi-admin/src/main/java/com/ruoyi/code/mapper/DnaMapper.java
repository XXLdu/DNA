package com.ruoyi.code.mapper;

import java.util.List;
import com.ruoyi.code.domain.Dna;

/**
 * DNA图谱信息（DNA）Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
public interface DnaMapper 
{
    /**
     * 查询DNA图谱信息（DNA）
     * 
     * @param id DNA图谱信息（DNA）ID
     * @return DNA图谱信息（DNA）
     */
    public Dna selectDnaById(String id);

    /**
     * 查询DNA图谱信息（DNA）列表
     * 
     * @param dna DNA图谱信息（DNA）
     * @return DNA图谱信息（DNA）集合
     */
    public List<Dna> selectDnaList(Dna dna);

    /**
     * 新增DNA图谱信息（DNA）
     * 
     * @param dna DNA图谱信息（DNA）
     * @return 结果
     */
    public int insertDna(Dna dna);

    /**
     * 修改DNA图谱信息（DNA）
     * 
     * @param dna DNA图谱信息（DNA）
     * @return 结果
     */
    public int updateDna(Dna dna);

    /**
     * 删除DNA图谱信息（DNA）
     * 
     * @param id DNA图谱信息（DNA）ID
     * @return 结果
     */
    public int deleteDnaById(String id);

    /**
     * 批量删除DNA图谱信息（DNA）
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDnaByIds(String[] ids);
}
