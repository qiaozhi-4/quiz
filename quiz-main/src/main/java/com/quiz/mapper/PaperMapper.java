package com.quiz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quiz.dto.PaperDTO;
import com.quiz.entity.Paper;

import java.util.List;

/**
 * <p>
 * 题目试卷 Mapper 接口
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-06
 */
public interface PaperMapper extends BaseMapper<Paper> {
    /**
     * 根据试卷id查询试卷信息
     *
     * @param paperId 试卷id
     */
    PaperDTO selectPaperById(Integer paperId);

    /**
     * 根据用户id查询试卷列表
     *
     * @param userId 用户id
     */
    List<PaperDTO> selectPaperListByUserId(Integer userId);
}
