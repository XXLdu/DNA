package com.ruoyi.code.mapper;

import com.ruoyi.code.domain.Suggestion;

public interface SuggestionMapper {

    /**
     * 新增审核记录（Suggestion）
     *
     * @param Suggestion 补充材料（Suggestion）
     * @return 结果
     */
    public int insertSuggestion(Suggestion Suggestion);

}
