package com.quiz.controller;


import com.quiz.entity.TPermission;
import com.quiz.entity.TUser;
import com.quiz.service.ITPermissionService;
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
@RequestMapping("/t-permission")
@Api(tags = "权限表 前端控制器")
@RequiredArgsConstructor
public class TPermissionController {
    private final ITPermissionService permissionService;

    @GetMapping("/test1")
    public List<TPermission> test1() {
        return permissionService.list();
    }

    @GetMapping("/test2")
    public List<TPermission> tes2() {
        throw new RuntimeException("测试异常");
//        return permissionService.list();
    }

    @GetMapping("/test3/{current:\\d+}/{size:\\d+}")
    public Map<String, Object> test3(@PathVariable("current") Integer current,
                                     @PathVariable("size") Integer size) {
        return permissionService.paging(current, size);
    }

    @PostMapping("test4")
    public List<TPermission> test4(@RequestBody TUser user){
        return permissionService.list();
    }
}
