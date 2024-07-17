package com.quiz.controller;


import com.quiz.annotation.PathPermission;
import com.quiz.entity.Path;
import com.quiz.enumerate.PermissionEnum;
import com.quiz.service.IPathService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 路径表 前端控制器
 * </p>
 *
 * @author XGeorge
 * @since 2024-07-16
 */
@RestController
@RequestMapping("/path")
@Api(tags = "路径表 前端控制器")
@RequiredArgsConstructor
public class PathController {
    private final IPathService pathService;

    @PathPermission(PermissionEnum.UPDATE)
    @ApiOperation("更新所有接口路径")
    @GetMapping("update-all-path")
    public List<Path> updateAllPath() {
        return pathService.updateAllPath();
    }

    @ApiOperation(value = "更新所有接口路径(不要权限)")
    @GetMapping("update-all-path-test")
    public List<Path> updateAllPathTest() {
        return pathService.updateAllPath();
    }
}
