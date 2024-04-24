package com.quiz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.entity.Answers;
import com.quiz.mapper.AnswersMapper;
import com.quiz.service.IAnswersService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 题目答卷 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-24
 */
@Service
public class AnswersServiceImpl extends ServiceImpl<AnswersMapper, Answers> implements IAnswersService {

}
