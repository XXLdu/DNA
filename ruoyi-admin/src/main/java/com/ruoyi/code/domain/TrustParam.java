package com.ruoyi.code.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.Date;

/**
 * 委托对象 trust
 *
 * @author ruoyi
 * @date 2020-06-03
 */
public class TrustParam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID	主键 UUID() */
    private String id;

    /** 编号	系统按照编号规则自动生成 */
    @Excel(name = "编号	系统按照编号规则自动生成")
    private String code;

    /** 受理编号 */
    private String acceptcode;

    /** 委托时间 */
    @Excel(name = "委托时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;

    /** 委托单位编号	关联单位表主键 */
    @Excel(name = "委托单位编号	关联单位表主键")
    private String departmentId;

    /** 委托单位名称 */
    @Excel(name = "委托单位名称")
    private String departmentName;

    /** 鉴定专业	存value汉字 */
    @Excel(name = "鉴定专业	存value汉字")
    private String appraisalType;

    /** 姓名1 */
    @Excel(name = "姓名1")
    private String name1;

    /** 联系方式1 */
    @Excel(name = "联系方式1")
    private String tel1;

    /** 职务1 */
    @Excel(name = "职务1")
    private String job1;

    /** 证件类型1 */
    @Excel(name = "证件类型1")
    private String cardType1;

    /** 证件编号1 */
    @Excel(name = "证件编号1")
    private String cardCode1;

    /** 姓名2 */
    @Excel(name = "姓名2")
    private String name2;

    /** 联系方式2 */
    @Excel(name = "联系方式2")
    private String tel2;

    /** 职务2 */
    @Excel(name = "职务2")
    private String job2;

    /** 证件类型2	存value汉字 */
    @Excel(name = "证件类型2	存value汉字")
    private String cardType2;

    /** 证件编号2 */
    @Excel(name = "证件编号2")
    private String cardCode2;

    /** 鉴定要求 */
    @Excel(name = "鉴定要求")
    private String appraisalAsk;

    /** 鉴定方法要求 */
    @Excel(name = "鉴定方法要求")
    private String appraisalWayAsk;

    /** 原鉴定情况 */
    @Excel(name = "原鉴定情况")
    private String oldAppraisal;

    /** 案件名称 */
    @Excel(name = "案件名称")
    private String caseName;

    /** 案发地点 */
    @Excel(name = "案发地点")
    private String casePlace;

    /** 案发时间 */
    @Excel(name = "案发时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date caseTime;

    /** 案件性质	存value汉字 */
    @Excel(name = "案件性质	存value汉字")
    private String caseXingzhi;

    /** 案件类型	存value汉字 */
    @Excel(name = "案件类型	存value汉字")
    private String caseType;

    /** 案件级别	存value汉字 */
    @Excel(name = "案件级别	存value汉字")
    private String caseLevel;

    /** 简要案情 */
    @Excel(name = "简要案情")
    private String caseSummary;

    /** 流程id
     * 0，待提交【默认值】；10，所长退回；
     * 1，所长待审核；
     * 2，待二次提交；32，鉴定单位退回；
     * 3，待鉴定单位审核；
     * 4，待检验；
     * 5，待生成鉴定文书；65，授权人退回； 75，机构负责人退回；
     * 6，待授权人审核文书；76机构负责人退回；
     * 7，待机构负责人审核文书；
     * 8，完成。*/
    @Excel(name = "流程id")
    private ArrayList<String> processCode;

    /** 提醒时间/鉴定文书出具时间/鉴定结束时间	确认受理时计算21日后时间，补充材料和会诊会重新计算时间 */
    @Excel(name = "提醒时间/鉴定文书出具时间/鉴定结束时间	确认受理时计算21日后时间，补充材料和会诊会重新计算时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date appraisalEndTime;

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

    public String getAcceptcode() {
        return acceptcode;
    }

    public void setAcceptcode(String acceptcode) {
        this.acceptcode = acceptcode;
    }

    public void setTime(Date time)
    {
        this.time = time;
    }

    public Date getTime()
    {
        return time;
    }
    public void setDepartmentId(String departmentId)
    {
        this.departmentId = departmentId;
    }

    public String getDepartmentId()
    {
        return departmentId;
    }
    public void setDepartmentName(String departmentName)
    {
        this.departmentName = departmentName;
    }

    public String getDepartmentName()
    {
        return departmentName;
    }
    public void setAppraisalType(String appraisalType)
    {
        this.appraisalType = appraisalType;
    }

    public String getAppraisalType()
    {
        return appraisalType;
    }
    public void setName1(String name1)
    {
        this.name1 = name1;
    }

    public String getName1()
    {
        return name1;
    }
    public void setTel1(String tel1)
    {
        this.tel1 = tel1;
    }

    public String getTel1()
    {
        return tel1;
    }
    public void setJob1(String job1)
    {
        this.job1 = job1;
    }

    public String getJob1()
    {
        return job1;
    }
    public void setCardType1(String cardType1)
    {
        this.cardType1 = cardType1;
    }

    public String getCardType1()
    {
        return cardType1;
    }
    public void setCardCode1(String cardCode1)
    {
        this.cardCode1 = cardCode1;
    }

    public String getCardCode1()
    {
        return cardCode1;
    }
    public void setName2(String name2)
    {
        this.name2 = name2;
    }

    public String getName2()
    {
        return name2;
    }
    public void setTel2(String tel2)
    {
        this.tel2 = tel2;
    }

    public String getTel2()
    {
        return tel2;
    }
    public void setJob2(String job2)
    {
        this.job2 = job2;
    }

    public String getJob2()
    {
        return job2;
    }
    public void setCardType2(String cardType2)
    {
        this.cardType2 = cardType2;
    }

    public String getCardType2()
    {
        return cardType2;
    }
    public void setCardCode2(String cardCode2)
    {
        this.cardCode2 = cardCode2;
    }

    public String getCardCode2()
    {
        return cardCode2;
    }
    public void setAppraisalAsk(String appraisalAsk)
    {
        this.appraisalAsk = appraisalAsk;
    }

    public String getAppraisalAsk()
    {
        return appraisalAsk;
    }
    public void setAppraisalWayAsk(String appraisalWayAsk)
    {
        this.appraisalWayAsk = appraisalWayAsk;
    }

    public String getAppraisalWayAsk()
    {
        return appraisalWayAsk;
    }
    public void setOldAppraisal(String oldAppraisal)
    {
        this.oldAppraisal = oldAppraisal;
    }

    public String getOldAppraisal()
    {
        return oldAppraisal;
    }
    public void setCaseName(String caseName)
    {
        this.caseName = caseName;
    }

    public String getCaseName()
    {
        return caseName;
    }
    public void setCasePlace(String casePlace)
    {
        this.casePlace = casePlace;
    }

    public String getCasePlace()
    {
        return casePlace;
    }
    public void setCaseTime(Date caseTime)
    {
        this.caseTime = caseTime;
    }

    public Date getCaseTime()
    {
        return caseTime;
    }
    public void setCaseXingzhi(String caseXingzhi)
    {
        this.caseXingzhi = caseXingzhi;
    }

    public String getCaseXingzhi()
    {
        return caseXingzhi;
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
    public void setCaseSummary(String caseSummary)
    {
        this.caseSummary = caseSummary;
    }

    public String getCaseSummary()
    {
        return caseSummary;
    }

    public ArrayList<String> getProcessCode() {
        return processCode;
    }

    public void setProcessCode(ArrayList<String> processCode) {
        this.processCode = processCode;
    }

    public void setAppraisalEndTime(Date appraisalEndTime)
    {
        this.appraisalEndTime = appraisalEndTime;
    }

    public Date getAppraisalEndTime()
    {
        return appraisalEndTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("code", getCode())
                .append("time", getTime())
                .append("departmentId", getDepartmentId())
                .append("departmentName", getDepartmentName())
                .append("appraisalType", getAppraisalType())
                .append("name1", getName1())
                .append("tel1", getTel1())
                .append("job1", getJob1())
                .append("cardType1", getCardType1())
                .append("cardCode1", getCardCode1())
                .append("name2", getName2())
                .append("tel2", getTel2())
                .append("job2", getJob2())
                .append("cardType2", getCardType2())
                .append("cardCode2", getCardCode2())
                .append("appraisalAsk", getAppraisalAsk())
                .append("appraisalWayAsk", getAppraisalWayAsk())
                .append("oldAppraisal", getOldAppraisal())
                .append("caseName", getCaseName())
                .append("casePlace", getCasePlace())
                .append("caseTime", getCaseTime())
                .append("caseXingzhi", getCaseXingzhi())
                .append("caseType", getCaseType())
                .append("caseLevel", getCaseLevel())
                .append("caseSummary", getCaseSummary())
                .append("processCode", getProcessCode())
                .append("appraisalEndTime", getAppraisalEndTime())
                .toString();
    }
}
