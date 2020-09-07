package com.ruoyi.code.mapper;

import java.util.List;
import com.ruoyi.code.domain.Testrecord;

/**
 * 检验记录Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
public interface TestrecordMapper 
{
    /**
     * 查询检验记录
     * 
     * @param id 检验记录ID
     * @return 检验记录
     */
    public Testrecord selectTestrecordById(String id);

    /**
     * 查询检验记录
     *
     * @param trustId
     * @return 检验记录
     */
    public Testrecord selectTestrecordByTrustId(String trustId);
    /**
     * 查询检验记录列表
     * 
     * @param testrecord 检验记录
     * @return 检验记录集合
     */
    public List<Testrecord> selectTestrecordList(Testrecord testrecord);

    /**
     * 新增检验记录
     * 
     * @param testrecord 检验记录
     * @return 结果
     */
    public int insertTestrecord(Testrecord testrecord);

    /**
     * 修改检验记录
     * 
     * @param testrecord 检验记录
     * @return 结果
     */
    public int updateTestrecord(Testrecord testrecord);

    /**
     * 删除检验记录
     * 
     * @param id 检验记录ID
     * @return 结果
     */
    public int deleteTestrecordById(String id);

    /**
     * 批量删除检验记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTestrecordByIds(String[] ids);
}
