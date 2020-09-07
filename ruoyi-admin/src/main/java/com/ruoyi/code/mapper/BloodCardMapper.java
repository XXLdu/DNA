package com.ruoyi.code.mapper;

import com.ruoyi.code.domain.BloodCard;

import java.util.List;

/**
 * 血卡Mapper接口
 * 
 * @author ruoyi
 * @date 2020-07-19
 */
public interface BloodCardMapper 
{
    /**
     * 查询血卡
     * 
     * @param id 血卡ID
     * @return 血卡
     */
    public BloodCard selectBloodCardById(Long id);

    /**
     * 查询血卡列表
     * 
     * @param bloodCard 血卡
     * @return 血卡集合
     */
    public List<BloodCard> selectBloodCardList(BloodCard bloodCard);

    /**
     * 新增血卡
     * 
     * @param bloodCard 血卡
     * @return 结果
     */
    public int insertBloodCard(BloodCard bloodCard);

    /**
     * 修改血卡
     * 
     * @param bloodCard 血卡
     * @return 结果
     */
    public int updateBloodCard(BloodCard bloodCard);

    /**
     * 删除血卡
     * 
     * @param id 血卡ID
     * @return 结果
     */
    public int deleteBloodCardById(Long id);

    /**
     * 批量删除血卡
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBloodCardByIds(String[] ids);
}
