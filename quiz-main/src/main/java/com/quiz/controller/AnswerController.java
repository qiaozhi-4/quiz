package com.quiz.controller;


import com.quiz.annotation.PathPermission;
import com.quiz.dto.AnswerDTO;
import com.quiz.dto.PaperAndAnswerDTO;
import com.quiz.enumerate.PermissionEnum;
import com.quiz.service.IAnswerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 题目答卷 前端控制器
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-08
 */
@RestController
@RequestMapping("/answer")
@Api(tags = "题目答卷 前端控制器")
@RequiredArgsConstructor
public class AnswerController {

    private final IAnswerService answerService;

    @PathPermission(PermissionEnum.USER_CREATE)
    @ApiOperation("保存答卷")
    @PostMapping("save")
    public PaperAndAnswerDTO saveAnswer(@RequestBody PaperAndAnswerDTO answer) {
        return answerService.saveAnswer(answer);
    }

    @PathPermission(PermissionEnum.READ)
    @ApiOperation("查询答卷详情")
    @GetMapping("get/{answerId:\\d+}")
    public AnswerDTO getAnswer(@PathVariable Integer answerId) {
        return answerService.getAnswerById(answerId);
    }

    @PathPermission(PermissionEnum.READ)
    @ApiOperation("查询答卷列表")
    @GetMapping("get-list/{userId:\\d+}")
    public List<AnswerDTO> getAnswerList(@PathVariable Integer userId) {
        return answerService.getAnswerListByUserId(userId);
    }

    @PathPermission(PermissionEnum.READ)
    @ApiOperation("查询徽章列表")
    @GetMapping("get-badge-list/{userId:\\d+}")
    public List<AnswerDTO> getBadgeList(@PathVariable Integer userId) {
        return answerService.getBadgeListByUserId(userId);
    }

}
