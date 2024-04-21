package com.quiz.service.impl;

import com.quiz.entity.Question;
import com.quiz.mapper.QuestionMapper;
import com.quiz.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
