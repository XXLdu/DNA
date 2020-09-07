package com.ruoyi.code.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * DNA图谱信息（DNA）对象 dna
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
public class Dna extends BaseEntity
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

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 图谱说明 */
    @Excel(name = "图谱说明")
    private String caption;

    /** 检测时间 */
    @Excel(name = "检测时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;

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
    public void setCaption(String caption) 
    {
        this.caption = caption;
    }

    public String getCaption() 
    {
        return caption;
    }
    public void setTime(Date time) 
    {
        this.time = time;
    }

    public Date getTime() 
    {
        return time;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("trustId", getTrustId())
            .append("name", getName())
            .append("caption", getCaption())
            .append("time", getTime())
            .toString();
    }
}
