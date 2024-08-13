package com.quiz.controller;


import com.quiz.annotation.PathPermission;
import com.quiz.dto.PropDTO;
import com.quiz.entity.UserProp;
import com.quiz.enumerate.PermissionEnum;
import com.quiz.service.IPropService;
import com.quiz.service.IUserPropService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 道具表 前端控制器
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-09
 */
@RestController
@RequestMapping("/prop")
@Api(tags = "道具表 前端控制器")
@RequiredArgsConstructor
public class PropController {
    private final IPropService propService;
    private final IUserPropService userPropService;


    @PathPermission(PermissionEnum.READ)
    @ApiOperation("获取用户道具信息")
    @GetMapping("get/{userId:\\d+}")
    public List<PropDTO> getProps(@PathVariable Integer userId) {
        return propService.getPropsByUserId(userId);
    }

    @PathPermission(PermissionEnum.USER_UPDATE)
    @ApiOperation("使用道具")
    @PutMapping("use/{userId:\\d+}/{propId:\\d+}/{num:\\d+}")
    public UserProp useProp(@PathVariable Integer userId, @PathVariable Integer propId, @PathVariable Integer num) {
        return userPropService.useProp(userId, propId, num);
    }

    @PathPermission(PermissionEnum.USER_UPDATE)
    @ApiOperation("获取道具")
    @PutMapping("gain/{userId:\\d+}/{propId:\\d+}/{num:\\d+}")
    public UserProp gainProp(@PathVariable Integer userId, @PathVariable Integer propId, @PathVariable Integer num) {
        return userPropService.gainProp(userId, propId, num);
    }
}
