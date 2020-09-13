package com.ruoyi.code.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.code.domain.*;
import com.ruoyi.code.mapper.AppraisalfileMapper;
import com.ruoyi.code.mapper.SampleMapper;
import com.ruoyi.code.mapper.TestrecordMapper;
import com.ruoyi.code.mapper.TrustMapper;
import com.ruoyi.code.service.IAppraisalfileService;
import com.ruoyi.code.utils.GreateJdws;
import com.ruoyi.common.core.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 鉴定文书（APPRAISALFILE）Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
@Service
public class AppraisalfileServiceImpl implements IAppraisalfileService 
{
    @Autowired
    private AppraisalfileMapper appraisalfileMapper;

    @Autowired
    private TrustMapper trustMapper;

    @Autowired
    private SampleMapper sampleMapper;

    @Autowired
    private TestrecordMapper testrecordMapper;


    /**
     * 查询鉴定文书（APPRAISALFILE）
     * 
     * @param id 鉴定文书（APPRAISALFILE）ID
     * @return 鉴定文书（APPRAISALFILE）
     */
    @Override
    public Appraisalfile selectAppraisalfileById(String id)
    {
        return appraisalfileMapper.selectAppraisalfileById(id);
    }

    /**
     * 查询鉴定文书（APPRAISALFILE）列表
     * 
     * @param appraisalfile 鉴定文书（APPRAISALFILE）
     * @return 鉴定文书（APPRAISALFILE）
     */
    @Override
    public List<Appraisalfile> selectAppraisalfileList(Appraisalfile appraisalfile)
    {
        return appraisalfileMapper.selectAppraisalfileList(appraisalfile);
    }

    /**
     * 新增鉴定文书（APPRAISALFILE）
     * 
     * @param appraisalfile 鉴定文书（APPRAISALFILE）
     * @return 结果
     */
    @Override
    public int insertAppraisalfile(Appraisalfile appraisalfile)
    {
        return appraisalfileMapper.insertAppraisalfile(appraisalfile);
    }

    /**
     * 修改鉴定文书（APPRAISALFILE）
     * 
     * @param appraisalfile 鉴定文书（APPRAISALFILE）
     * @return 结果
     */
    @Override
    public int updateAppraisalfile(Appraisalfile appraisalfile)
    {
        return appraisalfileMapper.updateAppraisalfile(appraisalfile);
    }

    /**
     * 删除鉴定文书（APPRAISALFILE）对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAppraisalfileByIds(String ids)
    {
        return appraisalfileMapper.deleteAppraisalfileByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除鉴定文书（APPRAISALFILE）信息
     * 
     * @param id 鉴定文书（APPRAISALFILE）ID
     * @return 结果
     */
    @Override
    public int deleteAppraisalfileById(String id)
    {
        return appraisalfileMapper.deleteAppraisalfileById(id);
    }

    public String getAppraisalfileContent(String trustId){
        Trust trust = trustMapper.selectTrustById(trustId);
        AppraisalfileContent appraisalfileContent = new AppraisalfileContent();
        appraisalfileContent.setBh("");//编号
        appraisalfileContent.setWtdw(trust.getDepartmentName()+"");//委托单位
        appraisalfileContent.setSjr(trust.getName1()+"");//送检人
        appraisalfileContent.setSlrq(new SimpleDateFormat("yyyy年MM月dd日").format(trust.getTime())+"");//送检日期
        appraisalfileContent.setAjzy(trust.getCaseSummary()+"");//案件摘要
        //获取检材列表
        Sample sample = new Sample();
        sample.setTrustId(trustId);
        List<Sample> sampleList = sampleMapper.selectSampleList(sample);
        StringBuffer jcyb = new StringBuffer();
        for (int i=0;i<sampleList.size();i++) {
            sample = sampleList.get(i);
            jcyb.append(i+1+"、");
            jcyb.append("标记有");
            jcyb.append(sample.getName());
            jcyb.append("字样的的物证包装袋1各，内装XXX一个，表面可见XXX，剪/擦/粘/吸/刮取XXX，编号为");
            jcyb.append(trust.getAcceptCode());
            jcyb.append("号");
            jcyb.append("<br>");
        }
        appraisalfileContent.setJcyb(jcyb.toString()+"");//检材样本
        appraisalfileContent.setJdyq(trust.getAppraisalAsk()+"");//鉴定要求
        Testrecord testrecord = testrecordMapper.selectTestrecordByTrustId(trustId);
        if(testrecord!=null){
            String jyksrq = testrecord.getStartTime()!=null?new SimpleDateFormat("yyyy年MM月dd日").format(testrecord.getStartTime()):"";
            appraisalfileContent.setJyksrq(jyksrq+"");//检验开始日期
            appraisalfileContent.setJydd(testrecord.getTestPlace()+"");//检验地点
            appraisalfileContent.setJdyj(testrecord.getConclusion()+"");//鉴定意见
            appraisalfileContent.setDyjdr(testrecord.getTester1()+"");//第一鉴定人
            appraisalfileContent.setDejdr(testrecord.getTester2()+"");//第二鉴定人
            appraisalfileContent.setSqqzr(testrecord.getReviewer()+"");//授权签字人
        }
        appraisalfileContent.setDna("");//dna列表
        appraisalfileContent.setJyxlh("");//基因序列号
        appraisalfileContent.setTime(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));//时间

        Map map = JSON.parseObject(JSON.toJSONString(appraisalfileContent), Map.class);

        return GreateJdws.readwriteWord(map);
    }
}
