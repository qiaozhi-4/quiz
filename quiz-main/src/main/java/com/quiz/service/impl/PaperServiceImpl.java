package com.quiz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.dto.PaperDto;
import com.quiz.entity.Paper;
import com.quiz.entity.PaperQuestions;
import com.quiz.mapper.PaperMapper;
import com.quiz.service.IPaperQuestionsService;
import com.quiz.service.IPaperService;
import com.quiz.utils.Assert;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public PaperDto addPaper(PaperDto paperDto) {
        Paper paper = Paper.builder().build();
        BeanUtils.copyProperties(paperDto, paper);
        Assert.isTrue(paper.insert(), "试卷插入失败");
        paperDto.setPaperId(paper.getPaperId());
        val paperQuestionsList = paperDto.getQuestions().stream()
                .map(question -> PaperQuestions.builder().paperId(paperDto.getPaperId()).questionId(question.getQuestionId()).build()).collect(Collectors.toList());
        Assert.isTrue(paperQuestionsService.saveBatch(paperQuestionsList), "试卷插入失败");
        return paperDto;
    }

    @Override
    public PaperDto getPaper(Integer paperId) {
        return this.baseMapper.selectPaperById(paperId);
    }

    @Override
    public List<Paper> getPaperListByUserId(Integer userId) {
        return this.list(new LambdaQueryWrapper<Paper>().eq(Paper::getCreatorUserId, userId));
    }

    @Override
    public Boolean removePaperByPaperId(Integer paperId) {
        // 删除试卷关联的题目
        val boo1 = paperQuestionsService.remove(new LambdaQueryWrapper<PaperQuestions>().eq(PaperQuestions::getPaperId, paperId));
        // 删除试卷
        val boo2 = this.removeById(paperId);
        return boo1 && boo2;
    }
}
