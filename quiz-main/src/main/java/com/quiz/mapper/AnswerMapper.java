package com.quiz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quiz.dto.AnswerDTO;
import com.quiz.entity.Answer;

import java.util.List;

/**
 * <p>
 * 题目答卷 Mapper 接口
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-21
 */
public interface AnswerMapper extends BaseMapper<Answer> {
    /**
     * 获取用户总得分
     *
     * @param userId 用户id
     */
    Integer selectScoreByUserId(Integer userId);

    /**
     * 根据id查询答卷详情
     *
     * @param answerId 答卷id
     */
    AnswerDTO selectAnswerById(Integer answerId);

    /**
     * 根据用户id查询答卷列表
     *
     * @param userId 用户id
     */
    List<AnswerDTO> selectAnswerListByUserId(Integer userId);

    /**
     * 根据用户id查询徽章列表
     *
     * @param userId 用户id
     */
    List<AnswerDTO> selectBadgeListByUserId(Integer userId);
}
