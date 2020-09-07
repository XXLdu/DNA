package com.ruoyi.code.service.impl;

import com.ruoyi.code.domain.PhysicalDeliver;
import com.ruoyi.code.domain.Sample;
import com.ruoyi.code.domain.Testrecord;
import com.ruoyi.code.domain.Trust;
import com.ruoyi.code.mapper.PhysicalDeliverMapper;
import com.ruoyi.code.service.IPhysicalDeliverService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 物证流转Service业务层处理
 * 
 * @author dqs
 * @date 2020-06-06
 */
@Service
public class PhysicalDeliverServiceImpl implements IPhysicalDeliverService 
{
    @Autowired
    private PhysicalDeliverMapper physicalDeliverMapper;

    @Autowired
    private SampleServiceImpl sampleServiceImpl;

    @Autowired
    private TestrecordServiceImpl testrecordServiceImpl;

    @Autowired
    private SysUserServiceImpl sysUserServiceImpl;

    /**
     * 查询物证流转
     * 
     * @param id 物证流转ID
     * @return 物证流转
     */
    @Override
    public PhysicalDeliver selectPhysicalDeliverById(String id)
    {
        return physicalDeliverMapper.selectPhysicalDeliverById(id);
    }

    /**
     * 查询物证流转列表
     * 
     * @param physicalDeliver 物证流转
     * @return 物证流转
     */
    @Override
    public List<PhysicalDeliver> selectPhysicalDeliverList(PhysicalDeliver physicalDeliver)
    {
        return physicalDeliverMapper.selectPhysicalDeliverList(physicalDeliver);
    }

    /**
     * 新增物证流转
     * 
     * @param physicalDeliver 物证流转
     * @return 结果
     */
    @Override
    public int insertPhysicalDeliver(PhysicalDeliver physicalDeliver)
    {
        return physicalDeliverMapper.insertPhysicalDeliver(physicalDeliver);
    }

    /**
     * 修改物证流转
     * 
     * @param physicalDeliver 物证流转
     * @return 结果
     */
    @Override
    public int updatePhysicalDeliver(PhysicalDeliver physicalDeliver)
    {
        return physicalDeliverMapper.updatePhysicalDeliver(physicalDeliver);
    }

    /**
     * 删除物证流转对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePhysicalDeliverByIds(String ids)
    {
        return physicalDeliverMapper.deletePhysicalDeliverByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除物证流转信息
     * 
     * @param id 物证流转ID
     * @return 结果
     */
    @Override
    public int deletePhysicalDeliverById(String id)
    {
        return physicalDeliverMapper.deletePhysicalDeliverById(id);
    }

    /**
     * 添加物证流转初始信息
     *
     * @param trust
     * @return 结果
     */
    @Override
    public void addPhysicalDeliver(Trust trust){
        Sample sample = new Sample();
        sample.setTrustId(trust.getId());
        List<Sample> sampleList = sampleServiceImpl.selectSampleList(sample);

        Testrecord testrecord = new Testrecord();
        testrecord = testrecordServiceImpl.selectTestrecordByTrustId(trust.getId());
        PhysicalDeliver physicalDeliver = new PhysicalDeliver();
        for(int i=0;i<sampleList.size();i++){
            sample=sampleList.get(i);
            addPhysicalDeliverNo1(physicalDeliver, sample, testrecord);
            addPhysicalDeliverNo2(physicalDeliver, sample, testrecord);
        }
    };

    //提取人移交检验人
    private void addPhysicalDeliverNo1(PhysicalDeliver physicalDeliver,Sample sample,Testrecord testrecord){
        physicalDeliver.setId(UUID.randomUUID().toString().replaceAll("-",""));
        physicalDeliver.setPhysicalId(sample.getId());
        physicalDeliver.setFromPeopleName(sample.getExtractor());
        physicalDeliver.setToPeopleName(testrecord.getTester1());
        physicalDeliver.setPlace(testrecord.getTestPlace());
        physicalDeliver.setType("移交");
        physicalDeliver.setTime(sample.getExtractTime());
        physicalDeliver.setPhysicalName(sample.getName());
        physicalDeliverMapper.insertPhysicalDeliver(physicalDeliver);
    }

    //检验人移交物证保管员
    private void addPhysicalDeliverNo2(PhysicalDeliver physicalDeliver, Sample sample, Testrecord testrecord){
        physicalDeliver.setId(UUID.randomUUID().toString().replaceAll("-",""));
        physicalDeliver.setPhysicalId(sample.getId());
        physicalDeliver.setFromPeopleName(testrecord.getTester1());
        SysUser sysUser = new SysUser();
        sysUser.setRoleId(new Long(102));
        String wzbgy = sysUserServiceImpl.selectUserList(sysUser).get(0).getUserName();
        physicalDeliver.setToPeopleName(wzbgy);
        physicalDeliver.setPlace("物证保管室");
        physicalDeliver.setType("移交");
        physicalDeliver.setTime(new Date());
        physicalDeliver.setPhysicalName(sample.getName());
        physicalDeliverMapper.insertPhysicalDeliver(physicalDeliver);
    }
}
