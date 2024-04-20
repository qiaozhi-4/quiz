package com.quiz.controller;

import com.quiz.service.IWxUserService;
import com.quiz.utils.Result;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PostMapping("login")
    public Result<Object> login(@RequestParam String code) throws WxErrorException {
        return wxUserService.login(code);
    }

    @PostMapping("save")
    public Result<Object> save(@RequestBody Map<String, String> userInfo) {
        return wxUserService.saveUserInfo(Integer.parseInt(userInfo.get("userId")),
                userInfo.get("encryptedData"), userInfo.get("ivStr"));
    }

}
