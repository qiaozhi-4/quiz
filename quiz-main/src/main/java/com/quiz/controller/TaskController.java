package com.quiz.controller;


import com.quiz.annotation.PathPermission;
import com.quiz.dto.TaskDTO;
import com.quiz.enumerate.PermissionEnum;
import com.quiz.service.ITaskService;
import com.quiz.utils.JWTUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 任务数据 前端控制器
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-10
 */
@RestController
@RequestMapping("/task")
@Api(tags = "任务数据 前端控制器")
@RequiredArgsConstructor
public class TaskController {
    private final ITaskService taskService;
    @PathPermission(PermissionEnum.READ)
    @ApiOperation("获取全部任务")
    @GetMapping("get-all")
    public List<TaskDTO> getAll(@RequestHeader String token) {
        Integer userId = Integer.parseInt(JWTUtils.getMemberIdByJwtToken(token));
        return taskService.getAll(userId);
    }
    @PathPermission(PermissionEnum.USER_CREATE)
    @ApiOperation("完成任务,并获取奖励")
    @PostMapping("finish/{taskId:\\d+}")
    public TaskDTO finish(@PathVariable Integer taskId) {
        return null;
    }
}
