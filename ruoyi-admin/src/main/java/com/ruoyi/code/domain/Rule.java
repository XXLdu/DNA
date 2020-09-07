package com.ruoyi.code.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 配置表对象 rule
 * 
 * @author ruoyi
 * @date 2020-07-19
 */
public class Rule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private String id;

    /** null */
    @Excel(name = "null")
    private String code;

    /** null */
    @Excel(name = "null")
    private String name;

    /** null */
    @Excel(name = "null")
    private String content;

    /** null */
    @Excel(name = "null")
    private String longcontent;

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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setLongcontent(String longcontent)
    {
        this.longcontent = longcontent;
    }

    public String getLongcontent()
    {
        return longcontent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("name", getName())
            .append("content", getContent())
            .append("longtext", getLongcontent())
            .toString();
    }
}
