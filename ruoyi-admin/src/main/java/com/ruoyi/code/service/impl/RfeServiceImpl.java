package com.ruoyi.code.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.code.mapper.RfeMapper;
import com.ruoyi.code.domain.Rfe;
import com.ruoyi.code.service.IRfeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 补充材料（RFE）Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
@Service
public class RfeServiceImpl implements IRfeService 
{
    @Autowired
    private RfeMapper rfeMapper;

    /**
     * 查询补充材料（RFE）
     * 
     * @param id 补充材料（RFE）ID
     * @return 补充材料（RFE）
     */
    @Override
    public Rfe selectRfeById(String id)
    {
        return rfeMapper.selectRfeById(id);
    }

    /**
     * 查询补充材料（RFE）列表
     * 
     * @param rfe 补充材料（RFE）
     * @return 补充材料（RFE）
     */
    @Override
    public List<Rfe> selectRfeList(Rfe rfe)
    {
        return rfeMapper.selectRfeList(rfe);
    }

    /**
     * 新增补充材料（RFE）
     * 
     * @param rfe 补充材料（RFE）
     * @return 结果
     */
    @Override
    public int insertRfe(Rfe rfe)
    {
        return rfeMapper.insertRfe(rfe);
    }

    /**
     * 修改补充材料（RFE）
     * 
     * @param rfe 补充材料（RFE）
     * @return 结果
     */
    @Override
    public int updateRfe(Rfe rfe)
    {
        return rfeMapper.updateRfe(rfe);
    }

    /**
     * 删除补充材料（RFE）对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRfeByIds(String ids)
    {
        return rfeMapper.deleteRfeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除补充材料（RFE）信息
     * 
     * @param id 补充材料（RFE）ID
     * @return 结果
     */
    @Override
    public int deleteRfeById(String id)
    {
        return rfeMapper.deleteRfeById(id);
    }
}
