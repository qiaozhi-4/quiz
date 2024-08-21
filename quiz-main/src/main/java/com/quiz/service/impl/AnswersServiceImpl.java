package com.quiz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.dto.AnswersDTO;
import com.quiz.dto.PaperAndAnswersDTO;
import com.quiz.dto.QuestionDTO;
import com.quiz.entity.Answers;
import com.quiz.entity.PaperQuestions;
import com.quiz.mapper.AnswersMapper;
import com.quiz.mapper.PaperMapper;
import com.quiz.service.IAnswersService;
import com.quiz.service.IPaperQuestionsService;
import com.quiz.service.IUserPropService;
import com.quiz.utils.Assert;
import com.quiz.utils.BeanUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
public class AnswersServiceImpl extends ServiceImpl<AnswersMapper, Answers> implements IAnswersService {
    private final IUserPropService userPropService;
    private final PaperMapper paperMapper;
    private final IPaperQuestionsService paperQuestionsService;

    @Override
    public PaperAndAnswersDTO saveAnswers(PaperAndAnswersDTO paperAndAnswersDTO) {
        Answers answers = BeanUtils.copyProperties(paperAndAnswersDTO, Answers.class);
        /* 计算分数 */
        List<QuestionDTO> collect = paperAndAnswersDTO.getQuestions().stream()
                .filter(e -> e.getAnswersIndex().equals(e.getSelectIndex())).collect(Collectors.toList());
        answers.setScore(collect.size() / paperAndAnswersDTO.getQuestions().size() * 100);
        Assert.isTrue(answers.insertOrUpdate(), "保存/更新答卷失败");
        List<PaperQuestions> paperQuestionsList = paperAndAnswersDTO.getQuestions().stream().map(e ->
                PaperQuestions.builder()
                        .id(e.getId())
                        .answersId(answers.getAnswerId())
                        .questionId(e.getQuestionId())
                        .selectIndex(e.getSelectIndex())
                        .extraDescribe(e.getSelectDescribe()).build()
        ).collect(Collectors.toList());
        Assert.isTrue(paperQuestionsService.saveOrUpdateBatch(paperQuestionsList), "保存/更新答卷作答信息失败");
        /* 更新成就总分 */
        userPropService.gainProp(paperAndAnswersDTO.getResponderUserId(), 1, answers.getScore() - paperAndAnswersDTO.getScore());
        paperAndAnswersDTO.setScore(answers.getScore());
        return paperAndAnswersDTO;
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
