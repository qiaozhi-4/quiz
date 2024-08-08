package com.quiz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.entity.Answers;
import com.quiz.mapper.AnswersMapper;
import com.quiz.service.IAnswersService;
import com.quiz.service.IPropService;
import com.quiz.utils.Assert;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class AnswersServiceImpl extends ServiceImpl<AnswersMapper, Answers> implements IAnswersService {
    private final IPropService propService;

    @Override
    public Answers saveAnswers(Answers answers) {
        /* 先查询该用户是否做过这个试卷 */
        this.getOneOpt(new LambdaQueryWrapper<Answers>()
                        .eq(Answers::getResponderUserId, answers.getResponderUserId())
                        .eq(Answers::getPaperId, answers.getPaperId()))
                .ifPresent(value -> {
                    answers.setAnswerId(value.getAnswerId());
                    /* 这里扣除复活宝石 */
                    Assert.isTrue(propService.useProp(answers.getResponderUserId(), 2, 1), "使用复活宝石失败");
                });
        Assert.isTrue(answers.insertOrUpdate(), "保存答卷失败");
        return answers;
    }
}
