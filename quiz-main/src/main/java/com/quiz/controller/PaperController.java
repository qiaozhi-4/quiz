package com.quiz.controller;


import com.quiz.annotation.PathPermission;
import com.quiz.dto.PaperAndAnswerDTO;
import com.quiz.entity.Paper;
import com.quiz.entity.Question;
import com.quiz.enumerate.PermissionEnum;
import com.quiz.service.IPaperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 题目试卷 前端控制器
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-06
 */
@RestController
@RequestMapping("/paper")
@Api(tags = "题目试卷 前端控制器")
@RequiredArgsConstructor
public class PaperController {

    private final IPaperService paperService;

    @PathPermission(PermissionEnum.USER_CREATE)
    @ApiOperation("创建试卷")
    @GetMapping("createt/{userId:\\d+}/{questionNumber:\\d+}")
    public PaperAndAnswerDTO createPaper(@PathVariable Integer userId, @PathVariable Integer questionNumber) {
        return paperService.createPaper(userId, questionNumber);
    }

    @PathPermission(PermissionEnum.USER_UPDATE)
    @ApiOperation("试卷换题")
    @PutMapping("switch-question/{userId:\\d+}/{paperId:\\d+}/{questionId:\\d+}")
    public Question paperSwitchQuestion(@PathVariable Integer userId, @PathVariable Integer paperId, @PathVariable Integer questionId) {
        return paperService.paperSwitchQuestion(userId, paperId, questionId);
    }

    @PathPermission(PermissionEnum.USER_UPDATE)
    @ApiOperation("更新试卷")
    @PutMapping("update")
    public Boolean updatePaper(@RequestBody Paper paper) {
        return paperService.updatePaper(paper);
    }

    @PathPermission(PermissionEnum.READ)
    @ApiOperation("通过试卷ID,获取试卷详情")
    @GetMapping("get/{paperId:\\d+}")
    public PaperAndAnswerDTO getPaper(@PathVariable Integer paperId) {
        return paperService.getPaper(paperId);
    }

    @PathPermission(PermissionEnum.READ)
    @ApiOperation("获取试卷列表")
    @GetMapping("get-list/{userId:\\d+}")
    public List<PaperAndAnswerDTO> getPaperList(@PathVariable Integer userId) {
        return paperService.getPaperListByUserId(userId);
    }

    @PathPermission(PermissionEnum.READ)
    @ApiOperation("获取用户试卷总数(包括已删除),判断是否出过题")
    @GetMapping("get-total/{userId:\\d+}")
    public Long getPaperTotal(@PathVariable Integer userId) {
        return paperService.getPaperTotalByUserId(userId, false);
    }


    @PathPermission(PermissionEnum.USER_DELETE)
    @ApiOperation("删除试卷")
    @DeleteMapping("remove/{paperId:\\d+}")
    public Boolean removePaper(@PathVariable Integer paperId) {
        return paperService.removePaperByPaperId(paperId);
    }

    @PathPermission(PermissionEnum.READ)
    @ApiOperation("查询用户a试卷列表,以及用户b的回答信息")
    @GetMapping("get-list/{creatorUserId:\\d+}/{responderUserId:\\d+}")
    public List<PaperAndAnswerDTO> getPaperAndAnswerDTOList(@PathVariable Integer creatorUserId, @PathVariable Integer responderUserId) {
        return paperService.getPaperAndAnswerInfoListByUserId(creatorUserId, responderUserId);
    }

    @PathPermission(PermissionEnum.READ)
    @ApiOperation("通过试卷ID,获取试卷详情,以及用户回答信息")
    @GetMapping("get/{paperId:\\d+}/{responderUserId:\\d+}")
    public PaperAndAnswerDTO getPaperAndAnswerDTO(@PathVariable Integer paperId, @PathVariable Integer responderUserId) {
        return paperService.getPaperAndAnswerDTOByPaperIdAndUserId(paperId, responderUserId);
    }

}
