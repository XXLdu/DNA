package com.ruoyi.code.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 检验记录对象 testrecord
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
public class Testrecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 委托表主键 */
    @Excel(name = "委托表主键")
    private String trustId;

    /** 检验人1 */
    @Excel(name = "检验人1")
    private String tester1;

    /** 检验人2姓名 */
    @Excel(name = "检验人2")
    private String tester2;

    /** 检验复核人 */
    @Excel(name = "检验复核人")
    private String reviewer;

    /** 检验地点 */
    @Excel(name = "检验地点")
    private String testPlace;

    /** 检验室 */
    @Excel(name = "检验室")
    private String testHome;

    /** 温度 */
    @Excel(name = "温度")
    private Long temperarure;

    /** 湿度 */
    @Excel(name = "湿度")
    private Long humidity;

    /** 结论 */
    @Excel(name = "结论")
    private String conclusion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrustId() {
        return trustId;
    }

    public void setTrustId(String trustId) {
        this.trustId = trustId;
    }

    public String getTester1() {
        return tester1;
    }

    public void setTester1(String tester1) {
        this.tester1 = tester1;
    }

    public String getTester2() {
        return tester2;
    }

    public void setTester2(String tester2) {
        this.tester2 = tester2;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getTestPlace() {
        return testPlace;
    }

    public void setTestPlace(String testPlace) {
        this.testPlace = testPlace;
    }

    public String getTestHome() {
        return testHome;
    }

    public void setTestHome(String testHome) {
        this.testHome = testHome;
    }

    public Long getTemperarure() {
        return temperarure;
    }

    public void setTemperarure(Long temperarure) {
        this.temperarure = temperarure;
    }

    public Long getHumidity() {
        return humidity;
    }

    public void setHumidity(Long humidity) {
        this.humidity = humidity;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("trustId", getTrustId())
            .append("tester1", getTester1())
            .append("tester2", getTester2())
            .append("reviewer", getReviewer())
            .append("testPlace", getTestPlace())
            .append("testHome", getTestHome())
            .append("temperarure", getTemperarure())
            .append("humidity", getHumidity())
            .append("conclusion", getConclusion())
            .toString();
    }
}
