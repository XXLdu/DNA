package com.ruoyi.code.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 补充材料（RFE）对象 rfe
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
public class Rfe extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 编号 */
    @Excel(name = "编号")
    private String code;

    /** 委托表主键 */
    @Excel(name = "委托表主键")
    private String trustId;

    /** 补充开始时间 */
    @Excel(name = "补充开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 补充结束时间 */
    @Excel(name = "补充结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 补充说明 */
    @Excel(name = "补充说明")
    private String explain;

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
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setExplain(String explain) 
    {
        this.explain = explain;
    }

    public String getExplain() 
    {
        return explain;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("trustId", getTrustId())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("explain", getExplain())
            .toString();
    }
}
