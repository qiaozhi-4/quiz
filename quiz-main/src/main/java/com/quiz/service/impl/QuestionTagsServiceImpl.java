package com.quiz.service.impl;

import com.quiz.entity.QuestionTags;
import com.quiz.mapper.QuestionTagsMapper;
import com.quiz.service.IQuestionTagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 题目关联标签 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-10
 */
@Service
public class QuestionTagsServiceImpl extends ServiceImpl<QuestionTagsMapper, QuestionTags> implements IQuestionTagsService {

}
