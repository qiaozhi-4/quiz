package com.quiz.service.impl;

import com.quiz.entity.PaperQuestions;
import com.quiz.mapper.PaperQuestionsMapper;
import com.quiz.service.IPaperQuestionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 试卷关联题目 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-09
 */
@Service
public class PaperQuestionsServiceImpl extends ServiceImpl<PaperQuestionsMapper, PaperQuestions> implements IPaperQuestionsService {

}
