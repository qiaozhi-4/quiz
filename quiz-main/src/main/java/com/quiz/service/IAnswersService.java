package com.quiz.service;

import com.quiz.dto.AnswersDTO;
import com.quiz.entity.Answers;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
     *
     * @param answers 答题记录
     */
    Answers saveAnswers(Answers answers);

    /**
     * 获取用户总得分
     *
     * @param userId 用户id
     */
    Integer getScoreByUserId(Integer userId);

    /**
     * 根据id查询答卷详情
     *
     * @param answerId 答卷id
     */
    AnswersDTO getAnswersById(Integer answerId);

    /**
     * 根据用户id查询答卷列表
     *
     * @param userId 用户id
     */
    List<AnswersDTO> getAnswersListByUserId(Integer userId);
}
