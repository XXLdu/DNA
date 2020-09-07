package com.ruoyi.code.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 物证流转对象 physical_deliver
 * 
 * @author dqs
 * @date 2020-06-06
 */
public class PhysicalDeliver extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 编号编号 */
    @Excel(name = "编号编号")
    private String code;

    /** 物证ID */
    @Excel(name = "物证ID")
    private String physicalId;

    /** 物证名称 */
    @Excel(name = "物证名称")
    private String physicalName;

    /** 流转类型 */
    @Excel(name = "流转类型")
    private String type;

    /** 移交人ID */
    @Excel(name = "移交人ID")
    private String fromPeopleId;

    /** 移交人姓名 */
    @Excel(name = "移交人姓名")
    private String fromPeopleName;

    /** 接收人ID */
    @Excel(name = "接收人ID")
    private String toPeopleId;

    /** 接收人姓名 */
    @Excel(name = "接收人姓名")
    private String toPeopleName;

    /** 移交时间 */
    @Excel(name = "移交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;

    /** 移交地点（新地点） */
    @Excel(name = "移交地点", readConverterExp = "新=地点")
    private String place;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setPhysicalId(String physicalId) 
    {
        this.physicalId = physicalId;
    }

    public String getPhysicalId() 
    {
        return physicalId;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getPhysicalName() {
        return physicalName;
    }

    public void setPhysicalName(String physicalName) {
        this.physicalName = physicalName;
    }

    public String getType()
    {
        return type;
    }
    public void setFromPeopleId(String fromPeopleId) 
    {
        this.fromPeopleId = fromPeopleId;
    }

    public String getFromPeopleId() 
    {
        return fromPeopleId;
    }
    public void setFromPeopleName(String fromPeopleName) 
    {
        this.fromPeopleName = fromPeopleName;
    }

    public String getFromPeopleName() 
    {
        return fromPeopleName;
    }
    public void setToPeopleId(String toPeopleId) 
    {
        this.toPeopleId = toPeopleId;
    }

    public String getToPeopleId() 
    {
        return toPeopleId;
    }
    public void setToPeopleName(String toPeopleName) 
    {
        this.toPeopleName = toPeopleName;
    }

    public String getToPeopleName() 
    {
        return toPeopleName;
    }
    public void setTime(Date time) 
    {
        this.time = time;
    }

    public Date getTime() 
    {
        return time;
    }
    public void setPlace(String place) 
    {
        this.place = place;
    }

    public String getPlace() 
    {
        return place;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("physicalId", getPhysicalId())
            .append("physicalName", getPhysicalName())
            .append("type", getType())
            .append("fromPeopleId", getFromPeopleId())
            .append("fromPeopleName", getFromPeopleName())
            .append("toPeopleId", getToPeopleId())
            .append("toPeopleName", getToPeopleName())
            .append("time", getTime())
            .append("place", getPlace())
            .append("remark", getRemark())
            .toString();
    }
}
