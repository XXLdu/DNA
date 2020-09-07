package com.ruoyi.code.service.impl;

import com.ruoyi.code.domain.ProcessCode;
import com.ruoyi.code.domain.Suggestion;
import com.ruoyi.code.domain.Trust;
import com.ruoyi.code.domain.TrustParam;
import com.ruoyi.code.mapper.SuggestionMapper;
import com.ruoyi.code.mapper.TrustMapper;
import com.ruoyi.code.service.IPhysicalDeliverService;
import com.ruoyi.code.service.ITrustService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.web.controller.common.CommonController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 委托Service业务层处理
 *
 * @author ruoyi
 * @date 2020-06-03
 */
@Service
public class TrustServiceImpl implements ITrustService {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private TrustMapper trustMapper;

    @Autowired
    private SuggestionMapper suggestionMapper;

    @Autowired
    private  IPhysicalDeliverService physicalDeliverService;
    /**
     * 查询委托
     *
     * @param id 委托ID
     * @return 委托
     */
    @Override
    public Trust selectTrustById(String id) {
        return trustMapper.selectTrustById(id);
    }

    /**
     * 查询委托列表
     *
     * @param trustParam 委托查询参数实体类
     * @return 委托
     */
    @Override
    public List<Trust> selectTrustList(TrustParam trustParam) {
        return trustMapper.selectTrustList(trustParam);
    }

    /**
     * 新增委托
     *
     * @param trust 委托
     * @return 结果
     */
    @Override
    public int insertTrust(Trust trust) {
        return trustMapper.insertTrust(trust);
    }

    /**
     * 修改委托
     *
     * @param trust 委托
     * @return 结果
     */
    @Override
    public int updateTrust(Trust trust) {
        return trustMapper.updateTrust(trust);
    }

    /**
     * 删除委托对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTrustByIds(String ids) {
        return trustMapper.deleteTrustByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除委托信息
     *
     * @param id 委托ID
     * @return 结果
     */
    @Override
    public int deleteTrustById(String id) {
        return trustMapper.deleteTrustById(id);
    }

    /**
     * 委托流程
     *
     * @param suggestion
     * @return 结果
     */
    @Override
    public int trustProcess(Suggestion suggestion) {
        Trust trust = selectTrustById(suggestion.getParentid());
        String processCode = getProcessCode(trust.getProcessCode(), suggestion.getStatus());
        if("5".equals(processCode)&&"pass".equals(suggestion.getStatus())){
            physicalDeliverService.addPhysicalDeliver(trust);
        }

        //插入审核意见
        {
            suggestion.setId(UUID.randomUUID().toString().replaceAll("-",""));
            suggestion.setTime(new Date());
            suggestion.setUserId(ShiroUtils.getUserId()+"");
            suggestion.setUserName(ShiroUtils.getLoginName());
            suggestion.setCode(processCode);
            suggestionMapper.insertSuggestion(suggestion);
        }

        trust.setProcessCode(processCode);
        trust.setId(suggestion.getParentid());
        return trustMapper.updateTrust(trust);
    }

    //获取委托编号
    @Override
    public String getTrustCode() {
        String code = trustMapper.getTrustCode();
        if (StringUtils.isEmpty(code) || "null".equals(code)) {
            code = "001";
        }
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime());
        return dateName + code;
    }

    ;


    /**
     * 获取流程code
     *
     * @param fromCode 当前 processCode
     * @param status   审核状态 pass：通过，back：退回
     * @return toCode
     */
    public String getProcessCode(String fromCode, String status) {
        String toCode = fromCode;
        if ("pass".equals(status)) {
            switch (fromCode) {
                case ProcessCode.wtj:
                    toCode = ProcessCode.wtdwdsh;
                    break;
                case ProcessCode.wtdwth:
                    toCode = ProcessCode.wtdwdsh;
                    break;
                case ProcessCode.wtdwdsh:
                    toCode = ProcessCode.dectj;
                    break;
                case ProcessCode.dectj:
                    toCode = ProcessCode.jddwdsh;
                    break;
                case ProcessCode.jddwth:
                    toCode = ProcessCode.jddwdsh;
                    break;
                case ProcessCode.jddwdsh:
                    toCode = ProcessCode.djy;
                    break;
                case ProcessCode.djy:
                    toCode = ProcessCode.dscjdws;
                    break;
                case ProcessCode.dscjdws:
                    toCode = ProcessCode.dsqrshws;
                    break;
                case ProcessCode.sqrth:
                    toCode = ProcessCode.dsqrshws;
                    break;
                case ProcessCode.jgfzrthdsc:
                    toCode = ProcessCode.dsqrshws;
                    break;
                case ProcessCode.dsqrshws:
                    toCode = ProcessCode.djgfzrshws;
                    break;
                case ProcessCode.jgfzrthsqrsh:
                    toCode = ProcessCode.djgfzrshws;
                    break;
                case ProcessCode.djgfzrshws:
                    toCode = ProcessCode.end;
                    break;
                default:
                    toCode = fromCode;
                    break;
            }
        } else {
            switch (fromCode) {
                case ProcessCode.wtdwdsh:
                    toCode = ProcessCode.wtdwth;
                    break;
                case ProcessCode.jddwdsh:
                    toCode = ProcessCode.jddwth;
                    break;
                case ProcessCode.dsqrshws:
                    toCode = ProcessCode.sqrth;
                    break;
                case ProcessCode.djgfzrshws:
                    toCode = ProcessCode.jgfzrthsqrsh;
                    break;
                default:
                    toCode = fromCode;
                    break;
            }
        }
        //机构负责人退回待生成
        if ("jgfzrthdsc".equals(status)) {
            toCode = ProcessCode.jgfzrthdsc;
        }
        return toCode;
    }

}
