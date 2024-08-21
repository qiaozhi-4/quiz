package com.quiz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quiz.dto.PaperAndAnswersDTO;
import com.quiz.dto.PaperDTO;
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
    PaperDTO createPaper(Integer userId, Integer questionNumber);

    /**
     * 试卷换题
     *
     * @param userId     用户id
     * @param paperId    试卷id
     * @param questionId 需要换掉的题目id
     */
    Question paperSwitchQuestion(Integer userId, Integer paperId, Integer questionId);

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
    PaperDTO getPaper(Integer paperId);

    /**
     * 获取用户试卷列表
     *
     * @param userId 用户id
     * @return 试卷列表
     */
    List<PaperDTO> getPaperListByUserId(Integer userId);

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
     * 查询用户a试卷列表,以及用户b的回答信息
     *
     * @param creatorUserId   用户a id
     * @param responderUserId 用户b id
     */
    List<PaperDTO> getPaperAndAnswerInfoListByUserId(Integer creatorUserId, Integer responderUserId);

    /**
     * 通过试卷ID,获取试卷详情,以及用户回答信息
     *
     * @param paperId         试卷 id
     * @param responderUserId 用户 id
     */
    PaperAndAnswersDTO getPaperAndAnswerDTOByPaperIdAndUserId(Integer paperId, Integer responderUserId);
}
