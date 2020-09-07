package com.ruoyi.code.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 鉴定文书（APPRAISALFILE）对象 appraisalfile
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
public class Appraisalfile extends BaseEntity
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

    /** 上传时间 */
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;

    /** 发文号 */
    @Excel(name = "发文号")
    private String issuedCode;

    //鉴定文书内容
    private String content;

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
    public void setTime(Date time) 
    {
        this.time = time;
    }

    public Date getTime() 
    {
        return time;
    }
    public void setIssuedCode(String issuedCode) 
    {
        this.issuedCode = issuedCode;
    }

    public String getIssuedCode() 
    {
        return issuedCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("trustId", getTrustId())
            .append("time", getTime())
            .append("issuedCode", getIssuedCode())
            .append("content", getContent())
            .toString();
    }
}
