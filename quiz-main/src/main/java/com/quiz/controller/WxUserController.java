package com.quiz.controller;

import cn.binarywang.wx.miniapp.bean.security.WxMaMsgSecCheckCheckRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;

    @ApiOperation(value = "微信小程序登录")
    @PostMapping("login")
    public Result<Object> login(@RequestParam String miniappId, @RequestParam String code) throws WxErrorException {
        return wxUserService.login(miniappId, code);
    }

    @PathPermission(PermissionEnum.USER_UPDATE)
    @ApiOperation(value = "修改微信小程序用户信息")
    @PostMapping("update")
    public User updateUser(@RequestBody User user) {
        return wxUserService.updateUserInfo(user);
    }

    @PathPermission(PermissionEnum.USER_UPDATE)
    @ApiOperation(value = "验证输入文本是否合规")
    @PostMapping("verify-text")
    public Boolean verifyText(@RequestParam Integer userId,
                              @RequestParam String miniappId,
                              @RequestBody WxMaMsgSecCheckCheckRequest msgRequest) throws WxErrorException {
        return wxUserService.verifyText(userId, miniappId, msgRequest);
    }

}
