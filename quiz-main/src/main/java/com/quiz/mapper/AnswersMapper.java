package com.quiz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quiz.entity.Answers;

/**
 * <p>
 * 题目答卷 Mapper 接口
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-22  1124:07:48
 */
public interface AnswersMapper extends BaseMapper<Answers> {
    /**
     * 根据pageId查询总记录数
     *
     * @param pagerId 试卷id
     */
    Integer selectTotalByPagerId(Integer pagerId);
}
