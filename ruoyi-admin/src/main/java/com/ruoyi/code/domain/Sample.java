package com.ruoyi.code.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 检材对象 sample
 * 
 * @author dqs
 * @date 2020-07-08
 */
public class Sample extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 编号 */
    private String code;

    /** 委托ID */
    private String trustId;

    /** 物证ID */
    private String physicalId;

    /** 物证名称 */
    private String physicalName;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 种类 */
    @Excel(name = "种类")
    private String kind;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 数量 */
    @Excel(name = "数量")
    private Long amount;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 提取人 */
    @Excel(name = "提取人")
    private String extractor;

    /** 提取时间 */
    @Excel(name = "提取时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date extractTime;

    /** 提取部位 */
    @Excel(name = "提取部位")
    private String extractPart;

    /** 提取方法 */
    private String extractWay;

    /** 包装情况 */
    private String packCondition;

    /** 性状 */
    private String xingzhuang;

    /** 描述 */
    private String miaosu;

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
    public void setPhysicalId(String physicalId) 
    {
        this.physicalId = physicalId;
    }

    public String getPhysicalId() 
    {
        return physicalId;
    }
    public void setPhysicalName(String physicalName) 
    {
        this.physicalName = physicalName;
    }

    public String getPhysicalName() 
    {
        return physicalName;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setKind(String kind) 
    {
        this.kind = kind;
    }

    public String getKind() 
    {
        return kind;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setAmount(Long amount) 
    {
        this.amount = amount;
    }

    public Long getAmount() 
    {
        return amount;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setExtractor(String extractor) 
    {
        this.extractor = extractor;
    }

    public String getExtractor() 
    {
        return extractor;
    }
    public void setExtractTime(Date extractTime) 
    {
        this.extractTime = extractTime;
    }

    public Date getExtractTime() 
    {
        return extractTime;
    }
    public void setExtractPart(String extractPart) 
    {
        this.extractPart = extractPart;
    }

    public String getExtractPart() 
    {
        return extractPart;
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
    public void setXingzhuang(String xingzhuang) 
    {
        this.xingzhuang = xingzhuang;
    }

    public String getXingzhuang() 
    {
        return xingzhuang;
    }
    public void setMiaosu(String miaosu)
    {
        this.miaosu = miaosu;
    }

    public String getMiaosu()
    {
        return miaosu;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("trustId", getTrustId())
            .append("physicalId", getPhysicalId())
            .append("physicalName", getPhysicalName())
            .append("type", getType())
            .append("kind", getKind())
            .append("name", getName())
            .append("amount", getAmount())
            .append("unit", getUnit())
            .append("extractor", getExtractor())
            .append("extractTime", getExtractTime())
            .append("extractPart", getExtractPart())
            .append("extractWay", getExtractWay())
            .append("packCondition", getPackCondition())
            .append("xingzhuang", getXingzhuang())
            .append("miaosu", getMiaosu())
            .toString();
    }
}
