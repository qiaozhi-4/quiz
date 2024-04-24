package com.quiz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.dto.PaperDto;
import com.quiz.entity.Paper;
import com.quiz.entity.PaperQuestions;
import com.quiz.entity.PaperTags;
import com.quiz.mapper.PaperMapper;
import com.quiz.service.IPaperQuestionsService;
import com.quiz.service.IPaperService;
import com.quiz.service.IPaperTagsService;
import com.quiz.utils.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * <p>
 * 题目试卷 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-23  1124:39:06
 */
@Service
@RequiredArgsConstructor
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements IPaperService {

    private final IPaperQuestionsService paperQuestionsService;
    private final IPaperTagsService paperTagsService;

    @Override
    public Boolean savePaperInfo(PaperDto paperDto) {
        final Paper paper = PaperDto.convertFor(paperDto);
        /* 插入试卷主体 */
        Assert.isTrue(paper.insert(), "试卷插入失败");

        /* 批量插入试卷关联题目数据 */
        boolean pq = paperQuestionsService.saveBatch(paperDto.getQuestions().stream().map(question -> PaperQuestions.builder()
                        .paperId(paper.getPaperId())
                        .questionId(question.getQuestionId()).build())
                .collect(Collectors.toList()));
        Assert.isTrue(pq, "试卷插入失败");

        /* 批量插入试卷关联标签数据 */
        boolean pt = paperTagsService.saveBatch(paperDto.getTags().stream().map(tag -> PaperTags.builder()
                        .paperId(paper.getPaperId())
                        .tagId(tag.getTagId()).build())
                .collect(Collectors.toList()));
        Assert.isTrue(pt, "试卷插入失败");
        return true;
    }
}
