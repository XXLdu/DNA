package com.ruoyi.code.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 物证对象 physical
 * 
 * @author ruoyi
 * @date 2020-05-31
 */
public class Physical extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 编号
编号 */
    @Excel(name = "编号")
    private String code;

    /** 委托ID */
    @Excel(name = "委托ID")
    private String trustId;

    /** 物证名称 */
    @Excel(name = "物证名称")
    private String name;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 数量 */
    @Excel(name = "数量")
    private Long amout;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 提取人ID */
    @Excel(name = "提取人ID")
    private String extractorId;

    /** 提取人姓名 */
    @Excel(name = "提取人姓名")
    private String extractorName;

    /** 提取时间 */
    @Excel(name = "提取时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date extractTime;

    /** 提取部位 */
    @Excel(name = "提取部位")
    private String extractType;

    /** 提取方法 */
    @Excel(name = "提取方法")
    private String extractWay;

    /** 包装情况 */
    @Excel(name = "包装情况")
    private String packCondition;

    /** 形状 */
    @Excel(name = "形状")
    private String shape;

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
    public void setTrustId(String trustId) 
    {
        this.trustId = trustId;
    }

    public String getTrustId() 
    {
        return trustId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setAmout(Long amout) 
    {
        this.amout = amout;
    }

    public Long getAmout() 
    {
        return amout;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setExtractorId(String extractorId) 
    {
        this.extractorId = extractorId;
    }

    public String getExtractorId() 
    {
        return extractorId;
    }
    public void setExtractorName(String extractorName) 
    {
        this.extractorName = extractorName;
    }

    public String getExtractorName() 
    {
        return extractorName;
    }
    public void setExtractTime(Date extractTime) 
    {
        this.extractTime = extractTime;
    }

    public Date getExtractTime() 
    {
        return extractTime;
    }
    public void setExtractType(String extractType) 
    {
        this.extractType = extractType;
    }

    public String getExtractType() 
    {
        return extractType;
    }
    public void setExtractWay(String extractWay) 
    {
        this.extractWay = extractWay;
    }

    public String getExtractWay() 
    {
        return extractWay;
    }
    public void setPackCondition(String packCondition) 
    {
        this.packCondition = packCondition;
    }

    public String getPackCondition() 
    {
        return packCondition;
    }
    public void setShape(String shape) 
    {
        this.shape = shape;
    }

    public String getShape() 
    {
        return shape;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("trustId", getTrustId())
            .append("name", getName())
            .append("type", getType())
            .append("amout", getAmout())
            .append("unit", getUnit())
            .append("extractorId", getExtractorId())
            .append("extractorName", getExtractorName())
            .append("extractTime", getExtractTime())
            .append("extractType", getExtractType())
            .append("extractWay", getExtractWay())
            .append("packCondition", getPackCondition())
            .append("shape", getShape())
            .toString();
    }
}
