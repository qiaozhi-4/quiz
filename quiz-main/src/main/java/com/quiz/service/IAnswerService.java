package com.quiz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quiz.dto.AnswerDTO;
import com.quiz.dto.PaperAndAnswerDTO;
import com.quiz.entity.Answer;

import java.util.List;

/**
 * <p>
 * 题目答卷 服务类
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-08
 */
public interface IAnswerService extends IService<Answer> {
    /**
     * 保存答题记录
     *
     * @param answer 答题记录
     */
    PaperAndAnswerDTO saveAnswer(PaperAndAnswerDTO answer);

    /**
     * 获取用户总得分
     *
     * @param userId 用户id
     */
    Integer getScoreByUserId(Integer userId);

    /**
     * 根据用户id查询徽章列表
     *
     * @param userId 用户id
     */
    List<AnswerDTO> getBadgeListByUserId(Integer userId);
}
