package com.quiz.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.quiz.constant.Constants;
import com.quiz.entity.TUser;
import com.quiz.entity.TUserAuth;
import com.quiz.service.ITUserAuthService;
import com.quiz.service.ITUserService;
import com.quiz.service.IWxUserService;
import com.quiz.utils.JWTUtils;
import com.quiz.utils.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
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
public class WxUserServiceImpl implements IWxUserService {

    private final WxMaService wxMaService;
    private final ITUserAuthService userAuthService;
    private final ITUserService userService;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Result<Object> login(String code) throws WxErrorException {
        final WxMaJscode2SessionResult sessionInfo = wxMaService.getUserService().getSessionInfo(code);
        TUserAuth userAuth = userAuthService
                .getOne(new LambdaQueryWrapper<TUserAuth>().eq(TUserAuth::getProviderId, sessionInfo.getOpenid()));
        TUser user;
        Map<Object, Object> map = new HashMap<>();
        if (Objects.isNull(userAuth)) {
            log.info("当前用户第一次登录,openID:" + sessionInfo.getOpenid());
            map.put("isFirst", true);
            user = TUser.defUser();
            userService.save(user);
            userAuth = TUserAuth.builder()
                    .userId(user.getUserId())
                    .provider(Constants.WX_NAME)
                    .providerId(sessionInfo.getOpenid())
                    .build();
            userAuthService.save(userAuth);
            redisTemplate.opsForValue().set(Constants.REDIS_WX_SESSION + user.getUserId(),
                    sessionInfo.getSessionKey(), Duration.ofMinutes(10));
        } else {
            map.put("isFirst", false);
            user = userService.getById(userAuth.getUserId());
            user.setLastLoginAt(LocalDateTime.now());
            userService.updateById(user);
        }
        final String jwtToken = JWTUtils.getJwtToken(user.getUserId().toString(), user.getUsername());
        map.put(JWTUtils.TOKEN_KEY, jwtToken);
        map.put("userInfo", user);
        return Result.success(map);
    }

    @Override
    public Result<Object> saveUserInfo(Integer userId, String encryptedData, String ivStr) {
        final String sessionKey = (String) redisTemplate.opsForValue()
                .getAndDelete(Constants.REDIS_WX_SESSION + userId);
        final WxMaUserInfo userInfo = wxMaService.getUserService().getUserInfo(sessionKey, encryptedData, ivStr);
        final TUser user = TUser.builder()
                .userId(userId)
                .nickname(userInfo.getNickName())
                .avatarUrl(userInfo.getAvatarUrl())
                .build();
        return userService.updateById(user) ? Result.success() : Result.failed();
    }
}
