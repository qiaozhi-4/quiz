package com.quiz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quiz.dto.PaperAndAnswerDTO;
import com.quiz.entity.Paper;
import com.quiz.entity.Question;

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
     * 创建试卷
     *
     * @param userId         用户id
     * @param questionNumber 试卷包含的题目个数
     */
    PaperAndAnswerDTO createPaper(Integer userId, Integer questionNumber);

    /**
     * 试卷换题
     *
     * @param userId 用户id
     * @param pqId   试卷关联题目表id
     */
    Question paperSwitchQuestion(Integer userId, Integer pqId);

    /**
     * 更新试卷
     *
     * @param paper 试卷信息
     * @return 更新结果
     */
    Boolean updatePaper(PaperAndAnswerDTO paper);

    /**
     * 获取试卷
     *
     * @param paperId 试卷id
     * @return 试卷信息
     */
    PaperAndAnswerDTO getPaper(Integer paperId);

    /**
     * 获取用户试卷列表
     *
     * @param userId 用户id
     * @return 试卷列表
     */
    List<PaperAndAnswerDTO> getPaperListByUserId(Integer userId);

    /**
     * 获取用户试卷总数
     *
     * @param userId       用户id
     * @param filterDelete 是否需要过滤已删除
     */
    Long getPaperTotalByUserId(Integer userId, Boolean filterDelete);

    /**
     * 删除试卷
     *
     * @param paperId 试卷id
     * @return 删除结果
     */
    Boolean removePaperByPaperId(Integer paperId);

    /**
     * 获取试卷详情,以及用户回答信息
     *
     * @param paperId         试卷 id
     * @param responderUserId 答题用户 id,可以为空
     */
    PaperAndAnswerDTO getPaperAndAnswerDTO(Integer paperId, Integer responderUserId);

    /**
     * 获取试卷详情,以及用户回答信息列表
     *
     * @param creatorUserId   出题用户 id
     * @param responderUserId 答题用户 id,可以为空
     */
    List<PaperAndAnswerDTO> getPaperAndAnswerDTOList(Integer creatorUserId, Integer responderUserId);
}
