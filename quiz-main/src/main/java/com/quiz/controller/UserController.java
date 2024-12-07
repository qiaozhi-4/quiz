package com.quiz.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.quiz.annotation.PathPermission;
import com.quiz.dto.UserDTO;
import com.quiz.entity.Answer;
import com.quiz.entity.Paper;
import com.quiz.enumerate.PermissionEnum;
import com.quiz.service.IAnswerService;
import com.quiz.service.IPaperService;
import com.quiz.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-14  0924:57:20
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户信息表 前端控制器")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;
    private final IAnswerService answerService;
    private final IPaperService paperService;

    @PathPermission(PermissionEnum.READ)
    @ApiOperation("通过用户ID,获取用户信息")
    @GetMapping("get/{userId:\\d+}")
    public UserDTO getUser(@PathVariable Integer userId) {
        return userService.getUserDtoByUserId(userId);
    }

    @PathPermission(PermissionEnum.READ)
    @ApiOperation("通过用户ID,获取用户排行榜信息")
    @GetMapping("get-intimate-ranking/{userId:\\d+}")
    public List<UserDTO> getIntimateRanking(@PathVariable Integer userId) {
        return userService.getIntimateRanking(userId);
    }

    @PathPermission(PermissionEnum.READ)
    @ApiOperation("验证试卷是不是自己的,或者已经答过的")
    @GetMapping("verify-paper/{userId:\\d+}/{paperId:\\d+}")
    public Map<String, Boolean> verifyPaper(@PathVariable Integer userId, @PathVariable Integer paperId) {
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("isMyPaper", paperService.getOneOpt(new LambdaQueryWrapper<Paper>()
                        .eq(Paper::getPaperId, paperId).eq(Paper::getCreatorUserId, userId))
                .isPresent());
        map.put("isRepeatAnswer", answerService.getOneOpt(new LambdaQueryWrapper<Answer>()
                        .eq(Answer::getPaperId, paperId).eq(Answer::getResponderUserId, userId))
                .isPresent());
        return map;
    }
}
