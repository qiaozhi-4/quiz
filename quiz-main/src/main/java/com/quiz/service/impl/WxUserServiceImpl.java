package com.quiz.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.security.WxMaMsgSecCheckCheckRequest;
import cn.binarywang.wx.miniapp.bean.security.WxMaMsgSecCheckCheckResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.quiz.constant.Constants;
import com.quiz.entity.User;
import com.quiz.entity.UserAuth;
import com.quiz.mapper.UserMapper;
import com.quiz.service.IPropService;
import com.quiz.service.IUserAuthService;
import com.quiz.service.IUserService;
import com.quiz.service.IWxUserService;
import com.quiz.utils.Assert;
import com.quiz.utils.JWTUtils;
import com.quiz.utils.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * WxUserServiceImpl
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/19 21:56
 */
@Service
@RequiredArgsConstructor
@Log4j2
@CacheConfig(cacheNames = "user")
public class WxUserServiceImpl implements IWxUserService {

    private final WxMaService wxMaService;
    private final UserMapper userMapper;
    private final IUserService userService;
    private final IUserAuthService userAuthService;
    private final IPropService propService;

    @Override
    public Result<Object> login(String miniappId, String code) throws WxErrorException {
        Assert.isTrue(wxMaService.switchover(miniappId), "AppID错误");
        final WxMaJscode2SessionResult sessionInfo = wxMaService.getUserService().getSessionInfo(code);
        User user = userMapper.selectUserByProviderId(sessionInfo.getOpenid());

        Map<Object, Object> map = new HashMap<>();
        if (Objects.isNull(user)) {
            log.info("当前用户第一次登录,openID:{}", sessionInfo.getOpenid());
            /* 第三方注册新用户 */
            user = userService.registerUserTP(Constants.WX_NAME, sessionInfo.getOpenid());
        } else {
            Assert.isTrue(user.setLastLoginAt(LocalDateTime.now()).updateById(), "更新用户上次登录时间失败");
        }
        final String jwtToken = JWTUtils.getJwtToken(user.getUserId().toString(), user.getUsername());
        map.put(JWTUtils.TOKEN_KEY, jwtToken);
        map.put("userInfo", userMapper.selectUserDtoByUserId(user.getUserId()));
        map.put("props", propService.getPropsByUserId(user.getUserId()));
        return Result.success(map);
    }

    @CacheEvict(key = "#user.username")
    @Override
    public User updateUserInfo(User user) {
        user.updateById();
        return user;
    }

    @Override
    public Boolean verifyText(Integer userId, String miniappId, WxMaMsgSecCheckCheckRequest msgRequest) throws WxErrorException {
        Assert.isTrue(wxMaService.switchover(miniappId), "AppID错误");
        UserAuth userAuth = userAuthService.getOne(new LambdaQueryWrapper<UserAuth>().eq(UserAuth::getUserId, userId));
        msgRequest.setOpenid(userAuth.getProviderId());
        WxMaMsgSecCheckCheckResponse checkedMessage = wxMaService.getSecCheckService().checkMessage(msgRequest);
        Assert.isTrue(checkedMessage.getResult().getLabel().equals("100"), "内容违规");
        return true;
    }
}
