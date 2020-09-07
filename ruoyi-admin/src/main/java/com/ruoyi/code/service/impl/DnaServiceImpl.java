package com.ruoyi.code.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.code.mapper.DnaMapper;
import com.ruoyi.code.domain.Dna;
import com.ruoyi.code.service.IDnaService;
import com.ruoyi.common.core.text.Convert;

/**
 * DNA图谱信息（DNA）Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
@Service
public class DnaServiceImpl implements IDnaService 
{
    @Autowired
    private DnaMapper dnaMapper;

    /**
     * 查询DNA图谱信息（DNA）
     * 
     * @param id DNA图谱信息（DNA）ID
     * @return DNA图谱信息（DNA）
     */
    @Override
    public Dna selectDnaById(String id)
    {
        return dnaMapper.selectDnaById(id);
    }

    /**
     * 查询DNA图谱信息（DNA）列表
     * 
     * @param dna DNA图谱信息（DNA）
     * @return DNA图谱信息（DNA）
     */
    @Override
    public List<Dna> selectDnaList(Dna dna)
    {
        return dnaMapper.selectDnaList(dna);
    }

    /**
     * 新增DNA图谱信息（DNA）
     * 
     * @param dna DNA图谱信息（DNA）
     * @return 结果
     */
    @Override
    public int insertDna(Dna dna)
    {
        return dnaMapper.insertDna(dna);
    }

    /**
     * 修改DNA图谱信息（DNA）
     * 
     * @param dna DNA图谱信息（DNA）
     * @return 结果
     */
    @Override
    public int updateDna(Dna dna)
    {
        return dnaMapper.updateDna(dna);
    }

    /**
     * 删除DNA图谱信息（DNA）对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDnaByIds(String ids)
    {
        return dnaMapper.deleteDnaByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除DNA图谱信息（DNA）信息
     * 
     * @param id DNA图谱信息（DNA）ID
     * @return 结果
     */
    @Override
    public int deleteDnaById(String id)
    {
        return dnaMapper.deleteDnaById(id);
    }
}
