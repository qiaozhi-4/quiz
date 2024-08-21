package com.quiz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.entity.Question;
import com.quiz.mapper.QuestionMapper;
import com.quiz.service.IQuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 题目表 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-21  1024:23:35
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

    @Override
    public List<Question> getRandomQuestions(Integer userId, Integer num) {
        return this.baseMapper.selectRandomQuestionsByUserIdAndNumber(userId, num);
    }
}
