package com.quiz.service;

import com.quiz.entity.Answers;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 题目答卷 服务类
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-08
 */
public interface IAnswersService extends IService<Answers> {
    /**
     * 保存答题记录
     * @param answers 答题记录
     */
    Answers saveAnswers(Answers answers);

    /**
     * 获取用户总得分
     * @param userId 用户id
     */
    Integer getScoreByUserId(Integer userId);
}
