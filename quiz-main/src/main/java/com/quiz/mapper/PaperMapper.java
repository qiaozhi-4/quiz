package com.quiz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quiz.dto.PaperAndAnswerDTO;
import com.quiz.dto.PaperDTO;
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
    PaperDTO selectPaperByPaperId(Integer paperId);

    /**
     * 根据用户id查询试卷列表
     *
     * @param userId 用户id
     */
    List<PaperDTO> selectPaperListByUserId(Integer userId);

    /**
     * 查询用户a试卷列表,以及用户b的回答信息
     *
     * @param creatorUserId   用户a id
     * @param responderUserId 用户b id
     */
    List<PaperDTO> selectPaperAndAnswerInfoListByUserId(@Param("creatorUserId") Integer creatorUserId, @Param("responderUserId") Integer responderUserId);

    /**
     * 通过试卷ID,获取试卷详情,以及用户回答信息
     *
     * @param paperId   试卷 id
     * @param responderUserId 用户 id
     */
    PaperAndAnswerDTO selectPaperAndAnswerDTOListByPaperIdAndUserId(@Param("paperId") Integer paperId, @Param("responderUserId") Integer responderUserId);
}
