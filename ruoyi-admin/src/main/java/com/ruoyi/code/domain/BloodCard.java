package com.ruoyi.code.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 血卡对象 blood_card
 * 
 * @author ruoyi
 * @date 2020-07-19
 */
public class BloodCard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 人员类型 */
    @Excel(name = "人员类型")
    private String userType;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 别名 */
    private String alias;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 生日 */
    private Date birthday;

    /** 国籍 */
    private String nationality;

    /** 民族 */
    private String nation;

    /** 证件类型 */
    private String cardType;

    /** 职业 */
    private String job;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String cardCode;

    /** 户籍地 */
    private String hujidi;

    /** 家庭住址 */
    private String address;

    /** 涉案类型 */
    @Excel(name = "涉案类型")
    private String caseType;

    /** 涉案级别 */
    @Excel(name = "涉案级别")
    private String caseLevel;

    /** 填表单位 */
    @Excel(name = "填表单位")
    private String fillAgency;

    /** 填表人 */
    @Excel(name = "填表人")
    private String fillUser;

    /** 填表日期 */
    private Date fillTime;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setUserType(String userType) 
    {
        this.userType = userType;
    }

    public String getUserType() 
    {
        return userType;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setAlias(String alias) 
    {
        this.alias = alias;
    }

    public String getAlias() 
    {
        return alias;
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
    public void setNationality(String nationality) 
    {
        this.nationality = nationality;
    }

    public String getNationality() 
    {
        return nationality;
    }
    public void setNation(String nation) 
    {
        this.nation = nation;
    }

    public String getNation() 
    {
        return nation;
    }
    public void setCardType(String cardType) 
    {
        this.cardType = cardType;
    }

    public String getCardType() 
    {
        return cardType;
    }
    public void setJob(String job) 
    {
        this.job = job;
    }

    public String getJob() 
    {
        return job;
    }
    public void setCardCode(String cardCode) 
    {
        this.cardCode = cardCode;
    }

    public String getCardCode() 
    {
        return cardCode;
    }
    public void setHujidi(String hujidi) 
    {
        this.hujidi = hujidi;
    }

    public String getHujidi() 
    {
        return hujidi;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setCaseType(String caseType) 
    {
        this.caseType = caseType;
    }

    public String getCaseType() 
    {
        return caseType;
    }
    public void setCaseLevel(String caseLevel) 
    {
        this.caseLevel = caseLevel;
    }

    public String getCaseLevel() 
    {
        return caseLevel;
    }
    public void setFillAgency(String fillAgency) 
    {
        this.fillAgency = fillAgency;
    }

    public String getFillAgency() 
    {
        return fillAgency;
    }
    public void setFillUser(String fillUser) 
    {
        this.fillUser = fillUser;
    }

    public String getFillUser() 
    {
        return fillUser;
    }
    public void setFillTime(Date fillTime) 
    {
        this.fillTime = fillTime;
    }

    public Date getFillTime() 
    {
        return fillTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userType", getUserType())
            .append("name", getName())
            .append("alias", getAlias())
            .append("sex", getSex())
            .append("birthday", getBirthday())
            .append("nationality", getNationality())
            .append("nation", getNation())
            .append("cardType", getCardType())
            .append("job", getJob())
            .append("cardCode", getCardCode())
            .append("hujidi", getHujidi())
            .append("address", getAddress())
            .append("caseType", getCaseType())
            .append("caseLevel", getCaseLevel())
            .append("fillAgency", getFillAgency())
            .append("fillUser", getFillUser())
            .append("fillTime", getFillTime())
            .append("remark", getRemark())
            .toString();
    }
}
