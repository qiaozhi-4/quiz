package com.quiz.controller;


import com.quiz.annotation.PathPermission;
import com.quiz.entity.Question;
import com.quiz.enumerate.PermissionEnum;
import com.quiz.mapper.QuestionMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 题目表 前端控制器
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-21  1024:23:35
 */
@RestController
@RequestMapping("/question")
@Api(tags = "题目表 前端控制器")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionMapper questionMapper;

    @PathPermission(PermissionEnum.READ)
    @ApiOperation(value = "随机获取10个题目")
    @GetMapping("get-random-questions")
    public List<Question> getRandomQuestions() {
        return questionMapper.selectRandomQuestions();
    }

    @ApiOperation(value = "随机获取10个题目(不要权限)")
    @GetMapping("get-random-questions-test")
    public List<Question> getRandomQuestionsTest() {
        return questionMapper.selectRandomQuestions();
    }
}
