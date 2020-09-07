package com.ruoyi.code.service;

import java.util.List;
import com.ruoyi.code.domain.Rfe;

/**
 * 补充材料（RFE）Service接口
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
public interface IRfeService 
{
    /**
     * 查询补充材料（RFE）
     * 
     * @param id 补充材料（RFE）ID
     * @return 补充材料（RFE）
     */
    public Rfe selectRfeById(String id);

    /**
     * 查询补充材料（RFE）列表
     * 
     * @param rfe 补充材料（RFE）
     * @return 补充材料（RFE）集合
     */
    public List<Rfe> selectRfeList(Rfe rfe);

    /**
     * 新增补充材料（RFE）
     * 
     * @param rfe 补充材料（RFE）
     * @return 结果
     */
    public int insertRfe(Rfe rfe);

    /**
     * 修改补充材料（RFE）
     * 
     * @param rfe 补充材料（RFE）
     * @return 结果
     */
    public int updateRfe(Rfe rfe);

    /**
     * 批量删除补充材料（RFE）
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRfeByIds(String ids);

    /**
     * 删除补充材料（RFE）信息
     * 
     * @param id 补充材料（RFE）ID
     * @return 结果
     */
    public int deleteRfeById(String id);
}
