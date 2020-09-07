package com.ruoyi.code.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 鉴定详情对象 testrecord_detail
 * 
 * @author ruoyi
 * @date 2020-07-18
 */
public class TestrecordDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 委托id */
    private String trustId;

    /** 检材id 主键 */
    private String sampleId;

    /** 检材name*/
    private String sampleName;

    /** 方法（提纯） */
    private String purifyWay;

    /** 试剂（提纯） */
    private String purifyReagent;

    /** 仪器（提纯） */
    private String purifyInstrument;

    /** 开始时间（提纯） */
    private Date purifyStartTime;

    /** 结束时间（提纯） */
    private Date purifyEndTime;

    /** 方法（扩增） */
    private String amplificationWay;

    /** 试剂（扩增） */
    private String amplificationReagent;

    /** 仪器（扩增） */
    private String amplificationInstrument;

    /** 开始时间（扩增） */
    private Date amplificationStartTime;

    /** 结束时间（扩增） */
    private Date amplificationEndTime;

    /** 排序编号 */
    private String orderNum;

    public String getTrustId() {
        return trustId;
    }

    public void setTrustId(String trustId) {
        this.trustId = trustId;
    }

    public void setSampleId(String sampleId)
    {
        this.sampleId = sampleId;
    }

    public String getSampleId() 
    {
        return sampleId;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public void setPurifyWay(String purifyWay)
    {
        this.purifyWay = purifyWay;
    }

    public String getPurifyWay() 
    {
        return purifyWay;
    }
    public void setPurifyReagent(String purifyReagent) 
    {
        this.purifyReagent = purifyReagent;
    }

    public String getPurifyReagent() 
    {
        return purifyReagent;
    }
    public void setPurifyInstrument(String purifyInstrument) 
    {
        this.purifyInstrument = purifyInstrument;
    }

    public String getPurifyInstrument() 
    {
        return purifyInstrument;
    }
    public void setPurifyStartTime(Date purifyStartTime) 
    {
        this.purifyStartTime = purifyStartTime;
    }

    public Date getPurifyStartTime() 
    {
        return purifyStartTime;
    }
    public void setPurifyEndTime(Date purifyEndTime) 
    {
        this.purifyEndTime = purifyEndTime;
    }

    public Date getPurifyEndTime() 
    {
        return purifyEndTime;
    }
    public void setAmplificationWay(String amplificationWay) 
    {
        this.amplificationWay = amplificationWay;
    }

    public String getAmplificationWay() 
    {
        return amplificationWay;
    }
    public void setAmplificationReagent(String amplificationReagent) 
    {
        this.amplificationReagent = amplificationReagent;
    }

    public String getAmplificationReagent() 
    {
        return amplificationReagent;
    }
    public void setAmplificationInstrument(String amplificationInstrument) 
    {
        this.amplificationInstrument = amplificationInstrument;
    }

    public String getAmplificationInstrument() 
    {
        return amplificationInstrument;
    }
    public void setAmplificationStartTime(Date amplificationStartTime) 
    {
        this.amplificationStartTime = amplificationStartTime;
    }

    public Date getAmplificationStartTime() 
    {
        return amplificationStartTime;
    }
    public void setAmplificationEndTime(Date amplificationEndTime) 
    {
        this.amplificationEndTime = amplificationEndTime;
    }

    public Date getAmplificationEndTime() 
    {
        return amplificationEndTime;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("trustId", getTrustId())
            .append("sampleId", getSampleId())
            .append("sampleName", getSampleName())
            .append("purifyWay", getPurifyWay())
            .append("purifyReagent", getPurifyReagent())
            .append("purifyInstrument", getPurifyInstrument())
            .append("purifyStartTime", getPurifyStartTime())
            .append("purifyEndTime", getPurifyEndTime())
            .append("amplificationWay", getAmplificationWay())
            .append("amplificationReagent", getAmplificationReagent())
            .append("amplificationInstrument", getAmplificationInstrument())
            .append("amplificationStartTime", getAmplificationStartTime())
            .append("amplificationEndTime", getAmplificationEndTime())
            .append("orderNum", getOrderNum())
            .toString();
    }
}
