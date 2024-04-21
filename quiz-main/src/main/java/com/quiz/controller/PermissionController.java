package com.quiz.controller;


import com.quiz.entity.Permission;
import com.quiz.entity.User;
import com.quiz.service.IPermissionService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    private final IPermissionService permissionService;

    @GetMapping("/test1")
    public List<Permission> test1() {
        return permissionService.list();
    }

    @GetMapping("/test2")
    public List<Permission> tes2() {
        throw new RuntimeException("测试异常");
//        return permissionService.list();
    }

    @GetMapping("/test3/{current:\\d+}/{size:\\d+}")
    public Map<String, Object> test3(@PathVariable("current") Integer current,
                                     @PathVariable("size") Integer size) {
        return permissionService.paging(current, size);
    }

    @PostMapping("test4")
    public List<Permission> test4(@RequestBody User user){
        return permissionService.list();
    }
}
