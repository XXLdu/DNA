package com.ruoyi.code.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 被鉴定人对象 appraisedtor
 * 
 * @author ruoyi
 * @date 2020-05-31
 */
public class Appraisedtor extends BaseEntity
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

    /** 人员类型 */
    @Excel(name = "人员类型")
    private String type;

    /** 人员姓名 */
    @Excel(name = "人员姓名")
    private String name;

    /** 人员性别 */
    @Excel(name = "人员性别")
    private String sex;

    /** 出生日期 */
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String cardType;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String cardCode;

    /** 工作单位 */
    @Excel(name = "工作单位")
    private String company;

    /** 职业 */
    @Excel(name = "职业")
    private String job;

    /** 电话 */
    @Excel(name = "电话")
    private String tel;

    /** 地址 */
    @Excel(name = "地址")
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
    public void setTrustId(String trustId) 
    {
        this.trustId = trustId;
    }

    public String getTrustId() 
    {
        return trustId;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }
    public void setCardType(String cardType) 
    {
        this.cardType = cardType;
    }

    public String getCardType() 
    {
        return cardType;
    }
    public void setCardCode(String cardCode) 
    {
        this.cardCode = cardCode;
    }

    public String getCardCode() 
    {
        return cardCode;
    }
    public void setCompany(String company) 
    {
        this.company = company;
    }

    public String getCompany() 
    {
        return company;
    }
    public void setJob(String job) 
    {
        this.job = job;
    }

    public String getJob() 
    {
        return job;
    }
    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    public String getTel() 
    {
        return tel;
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
            .append("trustId", getTrustId())
            .append("type", getType())
            .append("name", getName())
            .append("sex", getSex())
            .append("birthday", getBirthday())
            .append("cardType", getCardType())
            .append("cardCode", getCardCode())
            .append("company", getCompany())
            .append("job", getJob())
            .append("tel", getTel())
            .append("place", getPlace())
            .toString();
    }
}
