package com.quiz.controller;


import com.quiz.annotation.PathPermission;
import com.quiz.dto.AnswersDTO;
import com.quiz.entity.Answers;
import com.quiz.enumerate.PermissionEnum;
import com.quiz.service.IAnswersService;
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
@RequestMapping("/answers")
@Api(tags = "题目答卷 前端控制器")
@RequiredArgsConstructor
public class AnswersController {

    private final IAnswersService answersService;

    @PathPermission(PermissionEnum.USER_CREATE)
    @ApiOperation("保存答卷")
    @PostMapping("save")
    public Answers save(@RequestBody Answers answers) {
        return answersService.saveAnswers(answers);
    }

    @PathPermission(PermissionEnum.READ)
    @ApiOperation("查询答卷详情")
    @GetMapping("get/{answerId:\\d+}")
    public AnswersDTO getAnswers(@PathVariable Integer answerId) {
        return answersService.getAnswersById(answerId);
    }

    @PathPermission(PermissionEnum.READ)
    @ApiOperation("查询答卷列表")
    @GetMapping("get-list/{userId:\\d+}")
    public List<AnswersDTO> getAnswersList(@PathVariable Integer userId) {
        return answersService.getAnswersListByUserId(userId);
    }

    @PathPermission(PermissionEnum.READ)
    @ApiOperation("查询徽章列表")
    @GetMapping("get-badge-list/{userId:\\d+}")
    public List<AnswersDTO> getBadgeList(@PathVariable Integer userId) {
        return answersService.getBadgeListByUserId(userId);
    }

}
