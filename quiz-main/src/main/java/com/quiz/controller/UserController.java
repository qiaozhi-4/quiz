package com.quiz.controller;


import com.quiz.annotation.PathPermission;
import com.quiz.dto.UserDTO;
import com.quiz.entity.User;
import com.quiz.enumerate.PermissionEnum;
import com.quiz.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @PathPermission(PermissionEnum.READ)
    @ApiOperation("通过用户ID,获取用户信息")
    @GetMapping("get/{userId:\\d+}")
    public User get(@PathVariable Integer userId) {
        return userService.getUserById(userId);
    }
    @PathPermission(PermissionEnum.READ)
    @ApiOperation("通过用户ID,获取用户排行榜信息")
    @GetMapping("get-intimate-ranking/{userId:\\d+}")
    public List<UserDTO> getIntimateRanking(@PathVariable Integer userId) {
        return userService.getIntimateRanking(userId);
    }
}
