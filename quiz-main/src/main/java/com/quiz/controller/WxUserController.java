package com.quiz.controller;

import com.quiz.annotation.PathPermission;
import com.quiz.entity.User;
import com.quiz.enumerate.PermissionEnum;
import com.quiz.service.IWxUserService;
import com.quiz.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * WxUserController
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/19 17:27
 */
@RestController
@RequestMapping("/wx-user")
@Api(tags = "微信相关接口")
@RequiredArgsConstructor
public class WxUserController {
    private final IWxUserService wxUserService;

    @ApiOperation(value = "微信小程序登录")
    @PostMapping("login")
    public Result<Object> login(@RequestParam String miniappId, @RequestParam String code) throws WxErrorException {
        return wxUserService.login(miniappId, code);
    }

    @PathPermission(PermissionEnum.USER_UPDATE)
    @ApiOperation(value = "修改微信小程序用户信息")
    @PostMapping("update")
    public User update(@RequestBody User user) {
        return wxUserService.updateUserInfo(user);
    }

}
