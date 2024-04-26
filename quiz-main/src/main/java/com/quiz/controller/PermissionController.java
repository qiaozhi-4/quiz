package com.quiz.controller;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-14  0924:57:20
 */
@RestController
@RequestMapping("/permission")
@Api(tags = "权限表 前端控制器")
@RequiredArgsConstructor
public class PermissionController {
}
