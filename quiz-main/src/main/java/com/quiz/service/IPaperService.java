package com.quiz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quiz.dto.PaperDto;
import com.quiz.entity.Paper;

import java.util.List;

/**
 * <p>
 * 题目试卷 服务类
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-06
 */
public interface IPaperService extends IService<Paper> {

    /**
     * 添加试卷
     *
     * @param paperDto 试卷信息
     */
    PaperDto savePaper(PaperDto paperDto);

    /**
     * 更新试卷
     *
     * @param paper 试卷信息
     * @return 更新结果
     */
    Boolean updatePaper(Paper paper);

    /**
     * 获取试卷
     *
     * @param paperId 试卷id
     * @return 试卷信息
     */
    PaperDto getPaper(Integer paperId);

    /**
     * 获取用户试卷列表
     *
     * @param userId 用户id
     * @return 试卷列表
     */
    List<PaperDto> getPaperListByUserId(Integer userId);

    /**
     * 删除试卷
     *
     * @param paperId 试卷id
     * @return 删除结果
     */
    Boolean removePaperByPaperId(Integer paperId);
}
