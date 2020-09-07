package com.ruoyi.code.service.impl;

import com.ruoyi.code.domain.BloodCard;
import com.ruoyi.code.mapper.BloodCardMapper;
import com.ruoyi.code.service.IBloodCardService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 血卡Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-19
 */
@Service
public class BloodCardServiceImpl implements IBloodCardService 
{
    @Autowired
    private BloodCardMapper bloodCardMapper;

    /**
     * 查询血卡
     * 
     * @param id 血卡ID
     * @return 血卡
     */
    @Override
    public BloodCard selectBloodCardById(Long id)
    {
        return bloodCardMapper.selectBloodCardById(id);
    }

    /**
     * 查询血卡列表
     * 
     * @param bloodCard 血卡
     * @return 血卡
     */
    @Override
    public List<BloodCard> selectBloodCardList(BloodCard bloodCard)
    {
        return bloodCardMapper.selectBloodCardList(bloodCard);
    }

    /**
     * 新增血卡
     * 
     * @param bloodCard 血卡
     * @return 结果
     */
    @Override
    public int insertBloodCard(BloodCard bloodCard)
    {
        return bloodCardMapper.insertBloodCard(bloodCard);
    }

    /**
     * 修改血卡
     * 
     * @param bloodCard 血卡
     * @return 结果
     */
    @Override
    public int updateBloodCard(BloodCard bloodCard)
    {
        return bloodCardMapper.updateBloodCard(bloodCard);
    }

    /**
     * 删除血卡对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBloodCardByIds(String ids)
    {
        return bloodCardMapper.deleteBloodCardByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除血卡信息
     * 
     * @param id 血卡ID
     * @return 结果
     */
    @Override
    public int deleteBloodCardById(Long id)
    {
        return bloodCardMapper.deleteBloodCardById(id);
    }
}
