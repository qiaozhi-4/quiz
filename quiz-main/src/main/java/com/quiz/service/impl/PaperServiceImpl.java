package com.quiz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.dto.PaperAndAnswerDTO;
import com.quiz.entity.Paper;
import com.quiz.entity.PaperQuestions;
import com.quiz.entity.Question;
import com.quiz.mapper.PaperMapper;
import com.quiz.mapper.QuestionMapper;
import com.quiz.service.IPaperQuestionsService;
import com.quiz.service.IPaperService;
import com.quiz.utils.Assert;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 题目试卷 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-06
 */
@Service
@RequiredArgsConstructor
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements IPaperService {

    private final IPaperQuestionsService paperQuestionsService;
    private final QuestionMapper questionMapper;

    @Transactional
    @Override
    public PaperAndAnswerDTO createPaper(Integer userId, Integer questionNumber) {
        List<PaperAndAnswerDTO> paperAndAnswerDTOList = this.baseMapper.selectPaperListByUserId(userId).stream()
                .filter(paperAndAnswerDTO -> paperAndAnswerDTO.getQuestions().stream()
                        .anyMatch(questionDTO -> !Optional.ofNullable(questionDTO.getPqSelectIndex()).isPresent()))
                .collect(Collectors.toList());
        if (!paperAndAnswerDTOList.isEmpty()) {
            return paperAndAnswerDTOList.get(0);
        }
        Paper paper = Paper.builder().creatorUserId(userId).build();
        // 获取当前用户的试卷列表的最大序号
        val lastPaper = this.getOneOpt(new LambdaQueryWrapper<Paper>().eq(Paper::getCreatorUserId, userId)
                .orderByDesc(Paper::getOrder).last("LIMIT 1"));
        // 设置试卷的序号,如果没有出过则给默认1
        paper.setOrder(lastPaper.map(value -> value.getOrder() + 1).orElse(1));
        Assert.isTrue(paper.insert(), "试卷生成失败");
        List<Question> questionList = questionMapper.selectRandomQuestionsByUserIdAndNumber(userId, questionNumber);
        Assert.isTrue(questionList.size() == questionNumber, "没有更多题目了");
        List<PaperQuestions> paperQuestionsList = questionList.stream()
                .map(question -> PaperQuestions.builder().paperId(paper.getPaperId()).questionId(question.getQuestionId()).build())
                .collect(Collectors.toList());
        Assert.isTrue(paperQuestionsService.saveBatch(paperQuestionsList), "试卷与题目建立关联失败");
        return this.baseMapper.selectPaperByPaperId(paper.getPaperId());
    }

    @Override
    public Question paperSwitchQuestion(Integer userId, Integer pqId) {
        List<Question> questionList = questionMapper.selectRandomQuestionsByUserIdAndNumber(userId, 1);
        Assert.isTrue(!questionList.isEmpty(), "没有更多题目了");
        PaperQuestions paperQuestions = PaperQuestions.builder().pqId(pqId).questionId(questionList.get(0).getQuestionId()).build();
        Assert.isTrue(paperQuestions.updateById(), "题目切换失败");
        return questionList.get(0);
    }

    @Override
    public Boolean updatePaper(PaperAndAnswerDTO paper) {
        List<PaperQuestions> paperQuestionsList = paper.getQuestions().stream()
                .map(questionDTO -> PaperQuestions.builder()
                        .pqId(questionDTO.getPqId())
                        .pqSelectIndex(questionDTO.getPqSelectIndex())
                        .pqExtraDescribe(questionDTO.getPqExtraDescribe())
                        .build())
                .collect(Collectors.toList());
        Assert.isTrue(paperQuestionsService.updateBatchById(paperQuestionsList), "试卷更新失败");
        return true;
    }

    @Override
    public PaperAndAnswerDTO getPaper(Integer paperId) {
        return this.baseMapper.selectPaperByPaperId(paperId);
    }

    @Override
    public List<PaperAndAnswerDTO> getPaperListByUserId(Integer userId) {
        return this.baseMapper.selectPaperListByUserId(userId);
    }

    @Override
    public Long getPaperTotalByUserId(Integer userId, Boolean filterDelete) {
        val queryWrapper = new LambdaQueryWrapper<Paper>();
        queryWrapper.eq(Paper::getCreatorUserId, userId);
        if (filterDelete) {
            queryWrapper.ne(Paper::getState, -1);
        }
        return this.baseMapper.selectCount(queryWrapper);
    }

    @Override
    public Boolean removePaperByPaperId(Integer paperId) {
        val paper = this.getById(paperId).setState(-1);
        return this.updateById(paper);
    }

    @Override
    public PaperAndAnswerDTO getPaperAndAnswerDTO(Integer paperId, Integer responderUserId) {
        return this.baseMapper.selectPaperAndAnswerDTO(paperId, responderUserId);
    }

    @Override
    public List<PaperAndAnswerDTO> getPaperAndAnswerDTOList(Integer creatorUserId, Integer responderUserId) {
        return this.baseMapper.selectPaperAndAnswerDTOList(creatorUserId, responderUserId);
    }
}
