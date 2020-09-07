package com.ruoyi.code.mapper;

import java.util.List;
import com.ruoyi.code.domain.Trust;
import com.ruoyi.code.domain.TrustParam;

/**
 * 委托Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-03
 */
public interface TrustMapper 
{
    /**
     * 查询委托
     * 
     * @param id 委托ID
     * @return 委托
     */
    public Trust selectTrustById(String id);

    /**
     * 查询委托列表
     * 
     * @param trustParam 委托查询参数
     * @return 委托集合
     */
    public List<Trust> selectTrustList(TrustParam trustParam);

    /**
     * 新增委托
     * 
     * @param trust 委托
     * @return 结果
     */
    public int insertTrust(Trust trust);

    /**
     * 修改委托
     * 
     * @param trust 委托
     * @return 结果
     */
    public int updateTrust(Trust trust);

    /**
     * 删除委托
     * 
     * @param id 委托ID
     * @return 结果
     */
    public int deleteTrustById(String id);

    /**
     * 批量删除委托
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTrustByIds(String[] ids);

    /**
     * 获取委托编号
     */
    public String getTrustCode();
}
