package com.quiz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quiz.dto.PaperAndAnswerDTO;
import com.quiz.entity.Paper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 题目试卷 Mapper 接口
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-21
 */
public interface PaperMapper extends BaseMapper<Paper> {
    /**
     * 根据试卷id查询试卷信息
     *
     * @param paperId 试卷id
     */
    PaperAndAnswerDTO selectPaperByPaperId(Integer paperId);

    /**
     * 根据用户id查询试卷列表
     *
     * @param userId 用户id
     */
    List<PaperAndAnswerDTO> selectPaperListByUserId(Integer userId);

    /**
     * 获取试卷详情,以及用户回答信息
     *
     * @param paperId         试卷 id
     * @param responderUserId 答题用户 id,可以为空
     */
    PaperAndAnswerDTO selectPaperAndAnswerDTO(@Param("paperId") Integer paperId,
                                              @Param("responderUserId") Integer responderUserId);

    /**
     * 获取试卷详情,以及用户回答信息列表
     *
     * @param creatorUserId   出题用户 id
     * @param responderUserId 答题用户 id,可以为空
     */
    List<PaperAndAnswerDTO> selectPaperAndAnswerDTOList(@Param("creatorUserId") Integer creatorUserId,
                                                        @Param("responderUserId") Integer responderUserId);
}
