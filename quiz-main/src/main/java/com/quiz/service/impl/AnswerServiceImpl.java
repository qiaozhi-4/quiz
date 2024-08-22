package com.quiz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.dto.AnswerDTO;
import com.quiz.dto.PaperAndAnswerDTO;
import com.quiz.entity.Answer;
import com.quiz.entity.AnswerQuestions;
import com.quiz.mapper.AnswerMapper;
import com.quiz.service.IAnswerQuestionsService;
import com.quiz.service.IAnswerService;
import com.quiz.service.IUserPropService;
import com.quiz.utils.Assert;
import com.quiz.utils.BeanUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements IAnswerService {
    private final IUserPropService userPropService;
    private final IAnswerQuestionsService answerQuestionsService;

    @Transactional
    @Override
    public PaperAndAnswerDTO saveAnswer(PaperAndAnswerDTO paperAndAnswerDTO) {
        Answer answer = BeanUtils.copyProperties(paperAndAnswerDTO, Answer.class);
        Assert.isTrue(!Objects.equals(paperAndAnswerDTO.getResponderUserId(), paperAndAnswerDTO.getCreatorUserId()), "不能回答自己出的问题");
        /* 如果回答过就扣除道具 */
        Optional.ofNullable(paperAndAnswerDTO.getAnswerId()).ifPresent(e -> userPropService.useProp(paperAndAnswerDTO.getResponderUserId(), 2, 1));
        /* 计算分数 */
        answer.setScore((int) paperAndAnswerDTO.getQuestions().stream()
                .filter(e -> e.getAqSelectIndex().equals(e.getPqSelectIndex()))
                .count() * 100 / paperAndAnswerDTO.getQuestions().size());
        Assert.isTrue(answer.insertOrUpdate(), "保存/更新答卷失败");
        List<AnswerQuestions> paperQuestionsList = paperAndAnswerDTO.getQuestions().stream().map(e ->
                AnswerQuestions.builder()
                        .aqId(e.getAqId())
                        .answerId(answer.getAnswerId())
                        .questionId(e.getQuestionId())
                        .aqSelectIndex(e.getAqSelectIndex())
                        .aqExtraDescribe(e.getAqExtraDescribe()).build()
        ).collect(Collectors.toList());
        Assert.isTrue(answerQuestionsService.saveOrUpdateBatch(paperQuestionsList), "保存/更新答卷作答信息失败");
        /* 更新成就总分 */
        userPropService.gainProp(paperAndAnswerDTO.getResponderUserId(), 1,
                answer.getScore() - Optional.ofNullable(paperAndAnswerDTO.getScore()).orElse(0));
        paperAndAnswerDTO.setScore(answer.getScore());
        return paperAndAnswerDTO;
    }

    @Override
    public Integer getScoreByUserId(Integer userId) {
        return this.baseMapper.selectScoreByUserId(userId);
    }

    @Override
    public AnswerDTO getAnswerById(Integer answerId) {
        return this.baseMapper.selectAnswerById(answerId);
    }

    @Override
    public List<AnswerDTO> getAnswerListByUserId(Integer userId) {
        return this.baseMapper.selectAnswerListByUserId(userId);
    }

    @Override
    public List<AnswerDTO> getBadgeListByUserId(Integer userId) {
        return this.baseMapper.selectBadgeListByUserId(userId);
    }
}
