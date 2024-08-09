package com.quiz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.constant.Constants;
import com.quiz.dto.AnswersDTO;
import com.quiz.entity.Answers;
import com.quiz.entity.Paper;
import com.quiz.mapper.AnswersMapper;
import com.quiz.mapper.PaperMapper;
import com.quiz.service.IAnswersService;
import com.quiz.service.IUserPropService;
import com.quiz.utils.Assert;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 题目答卷 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-08
 */
@Service
@AllArgsConstructor
public class AnswersServiceImpl extends ServiceImpl<AnswersMapper, Answers> implements IAnswersService {
    private final IUserPropService userPropService;
    private final PaperMapper paperMapper;

    @Override
    public Answers saveAnswers(Answers answers) {
        Paper paper = paperMapper.selectById(answers.getPaperId());
        Assert.isTrue(!paper.getCreatorUserId().equals(answers.getResponderUserId()), "不能回答自己出的问题");
        /* 计算分数 */
        val ans = paper.getAnswers().split(Constants.SPACE_MARK);
        val sel = answers.getSelects().split(Constants.SPACE_MARK);
        Assert.isTrue(ans.length == sel.length, "答案数量不匹配");
        answers.setScore(0);
        for (int i = 0; i < ans.length; i++) {
            if (ans[i].equals(sel[i])) {
                answers.setScore(answers.getScore() + 10);
            }
        }
        /* 先查询该用户是否做过这个试卷 */
        this.getOneOpt(new LambdaQueryWrapper<Answers>()
                        .eq(Answers::getResponderUserId, answers.getResponderUserId())
                        .eq(Answers::getPaperId, answers.getPaperId()))
                .ifPresent(value -> {
                    answers.setAnswerId(value.getAnswerId());
                    /* 这里扣除复活宝石 */
                    userPropService.useProp(answers.getResponderUserId(), 2, 1);
                });
        Assert.isTrue(answers.insertOrUpdate(), "保存答卷失败");
        /* 统计成就分数 */
        userPropService.setProp(answers.getResponderUserId(), 1, this.baseMapper.selectScoreByUserId(answers.getResponderUserId()));
        return answers;
    }

    @Override
    public Integer getScoreByUserId(Integer userId) {
        return this.baseMapper.selectScoreByUserId(userId);
    }

    @Override
    public AnswersDTO getAnswersById(Integer answerId) {
        return this.baseMapper.selectAnswersById(answerId);
    }

    @Override
    public List<AnswersDTO> getAnswersListByUserId(Integer userId) {
        return this.baseMapper.selectAnswersListByUserId(userId);
    }

    @Override
    public List<AnswersDTO> getBadgeListByUserId(Integer userId) {
        return this.baseMapper.selectBadgeListByUserId(userId);
    }
}
