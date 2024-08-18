package com.quiz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.dto.PaperDTO;
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
    public PaperDTO savePaper(PaperDTO paperDto) {
        // 创建试卷
        Paper paper = Paper.builder().build();
        BeanUtils.copyProperties(paperDto, paper);

        // 如果id已经存在,说明是更新
        if (paperDto.getPaperId() == null) {
            // 获取当前用户的试卷列表的最大序号
            val lastPaper = this.getOneOpt(new LambdaQueryWrapper<Paper>().eq(Paper::getCreatorUserId, paperDto.getCreatorUserId())
                    .orderByDesc(Paper::getOrder).last("LIMIT 1"));
            // 设置试卷的序号
            paper.setOrder(lastPaper.map(value -> value.getOrder() + 1).orElse(1));
            Assert.isTrue(paper.insert(), "试卷插入失败");
            paperDto.setPaperId(paper.getPaperId());
            val paperQuestionsList = paperDto.getQuestions().stream()
                    .map(question -> PaperQuestions.builder().paperId(paper.getPaperId()).questionId(question.getQuestionId()).build())
                    .collect(Collectors.toList());
            Assert.isTrue(paperQuestionsService.saveBatch(paperQuestionsList), "试卷插入失败");
        } else {
            Assert.isTrue(paper.updateById(), "试卷更新失败");
        }
        return paperDto;
    }

    @Override
    public Boolean updatePaper(Paper paper) {
        return this.updateById(paper);
    }

    @Override
    public PaperDTO getPaper(Integer paperId) {
        return this.baseMapper.selectPaperById(paperId);
    }

    @Override
    public List<PaperDTO> getPaperListByUserId(Integer userId) {
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
    public List<PaperDTO> getPaperAndAnswerInfoListByUserId(Integer creatorUserId, Integer responderUserId) {
        return this.baseMapper.selectPaperAndAnswerInfoListByUserId(creatorUserId, responderUserId);
    }
}
