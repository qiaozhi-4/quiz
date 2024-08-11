package com.quiz.controller;


import com.quiz.annotation.PathPermission;
import com.quiz.entity.UserProp;
import com.quiz.enumerate.PermissionEnum;
import com.quiz.service.IUserPropService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private final IUserPropService userPropService;

    @PathPermission(PermissionEnum.USER_UPDATE)
    @ApiOperation("使用道具")
    @PutMapping("use/{userId:\\d+}/{propId:\\d+}/{num:\\d+}")
    public UserProp use(@PathVariable Integer userId, @PathVariable Integer propId, @PathVariable Integer num) {
        return userPropService.useProp(userId, propId, num);
    }

    @PathPermission(PermissionEnum.USER_UPDATE)
    @ApiOperation("获取道具")
    @PutMapping("gain/{userId:\\d+}/{propId:\\d+}/{num:\\d+}")
    public UserProp gain(@PathVariable Integer userId, @PathVariable Integer propId, @PathVariable Integer num) {
        return userPropService.gainProp(userId, propId, num);
    }
}
