package com.quiz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quiz.dto.PaperDto;
import com.quiz.entity.Paper;

/**
 * <p>
 * 题目试卷 服务类
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-23  1124:39:06
 */
public interface IPaperService extends IService<Paper> {

    /**
     * 添加试卷
     *
     * @param paperDto 试卷信息
     */
    Boolean addPaperInfo(PaperDto paperDto);
}
