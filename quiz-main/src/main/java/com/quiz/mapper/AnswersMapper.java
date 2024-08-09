package com.quiz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quiz.dto.AnswersDTO;
import com.quiz.entity.Answers;

import java.util.List;

/**
 * <p>
 * 题目答卷 Mapper 接口
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-08
 */
public interface AnswersMapper extends BaseMapper<Answers> {
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
    AnswersDTO selectAnswersById(Integer answerId);

    /**
     * 根据用户id查询答卷列表
     *
     * @param userId 用户id
     */
    List<AnswersDTO> selectAnswersListByUserId(Integer userId);

    /**
     * 根据用户id查询徽章列表
     *
     * @param userId 用户id
     */
    List<AnswersDTO> selectBadgeListByUserId(Integer userId);
}
