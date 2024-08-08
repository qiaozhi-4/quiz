package com.quiz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quiz.entity.Answers;

/**
 * <p>
 * 题目答卷 服务类
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-24
 */
public interface IAnswersService extends IService<Answers> {
    /**
     * 保存答题记录
     * @param answers 答题记录
     */
    Answers saveAnswers(Answers answers);
}
