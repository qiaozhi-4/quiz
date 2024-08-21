package com.quiz.service.impl;

import com.quiz.entity.AnswerQuestions;
import com.quiz.mapper.AnswerQuestionsMapper;
import com.quiz.service.IAnswerQuestionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 答题关联题目 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-21
 */
@Service
public class AnswerQuestionsServiceImpl extends ServiceImpl<AnswerQuestionsMapper, AnswerQuestions> implements IAnswerQuestionsService {

}
