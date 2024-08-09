package com.quiz.service;

import com.quiz.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 题目表 服务类
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-21  1024:23:35
 */
public interface IQuestionService extends IService<Question> {
    /**
     * 随机返回 num 个题目
     *
     * @param userId 用户id
     * @param num    题目数量
     */
    List<Question> getRandomQuestions(Integer userId, Integer num);
}
