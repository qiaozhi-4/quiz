package com.quiz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.entity.PaperQuestions;
import com.quiz.mapper.PaperQuestionsMapper;
import com.quiz.service.IPaperQuestionsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 试卷关联题目 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-24
 */
@Service
public class PaperQuestionsServiceImpl extends ServiceImpl<PaperQuestionsMapper, PaperQuestions> implements IPaperQuestionsService {

}
