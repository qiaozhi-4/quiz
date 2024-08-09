package com.quiz.controller;


import com.quiz.annotation.PathPermission;
import com.quiz.entity.UserProp;
import com.quiz.enumerate.PermissionEnum;
import com.quiz.service.IUserPropService;
import com.quiz.utils.JWTUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("use/{propId:\\d+}/{num:\\d+}")
    public UserProp use(@RequestHeader String token, @PathVariable Integer propId, @PathVariable Integer num) {
        val userId = Integer.parseInt(JWTUtils.getMemberIdByJwtToken(token));
        return userPropService.useProp(userId, propId, num);
    }

    @PathPermission(PermissionEnum.USER_UPDATE)
    @ApiOperation("获取道具")
    @PutMapping("gain/{propId:\\d+}/{num:\\d+}")
    public UserProp gain(@RequestHeader String token, @PathVariable Integer propId, @PathVariable Integer num) {
        val userId = Integer.parseInt(JWTUtils.getMemberIdByJwtToken(token));
        return userPropService.gainProp(userId, propId, num);
    }
}
