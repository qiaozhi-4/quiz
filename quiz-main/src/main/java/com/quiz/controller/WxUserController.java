package com.quiz.controller;

import com.quiz.entity.User;
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

    @ApiOperation(value = "微信小程序登录", tags = {"Public"})
    @PostMapping("login")
    public Result<Object> login(@RequestParam String code) throws WxErrorException {
        return wxUserService.login(code);
    }

    @ApiOperation(value = "保存微信小程序用户信息", tags = {"Public"})
    @PostMapping("save")
    public Boolean save(@RequestBody User user) {
        return wxUserService.saveUserInfo(user);
    }

}
