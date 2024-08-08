package com.quiz.controller;


import com.quiz.annotation.PathPermission;
import com.quiz.entity.Answers;
import com.quiz.enumerate.PermissionEnum;
import com.quiz.service.IAnswersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
