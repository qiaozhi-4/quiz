package com.quiz.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.quiz.constant.Constants;
import com.quiz.dto.UserDto;
import com.quiz.entity.User;
import com.quiz.entity.UserAuth;
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
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Result<Object> login(String code) throws WxErrorException {
        final WxMaJscode2SessionResult sessionInfo = wxMaService.getUserService().getSessionInfo(code);
        UserAuth userAuth = UserAuth.builder().build()
                .selectOne(new LambdaQueryWrapper<UserAuth>().eq(UserAuth::getProviderId, sessionInfo.getOpenid()));
        User user;
        Map<Object, Object> map = new HashMap<>();
        if (Objects.isNull(userAuth)) {
            log.info("当前用户第一次登录,openID:" + sessionInfo.getOpenid());
            map.put("isFirst", true);
            user = UserDto.defUser();
            user.insert();
            userAuth = UserAuth.builder()
                    .userId(user.getUserId())
                    .provider(Constants.WX_NAME)
                    .providerId(sessionInfo.getOpenid())
                    .build();
            userAuth.insert();
        } else {
            map.put("isFirst", false);
            user = User.builder().userId(userAuth.getUserId()).build().selectById();
            user.setLastLoginAt(LocalDateTime.now());
            user.updateById();
        }
        redisTemplate.opsForValue().set(Constants.REDIS_WX_SESSION + user.getUserId(),
                sessionInfo.getSessionKey(), Duration.ofMinutes(10));
        final String jwtToken = JWTUtils.getJwtToken(user.getUserId().toString(), user.getUsername());
        map.put(JWTUtils.TOKEN_KEY, jwtToken);
        map.put("userInfo", user);
        return Result.success(map);
    }

    @Override
    public Result<Object> saveUserInfo(Integer userId, String encryptedData, String ivStr) {
        final String sessionKey = (String) redisTemplate.opsForValue()
                .getAndExpire(Constants.REDIS_WX_SESSION + userId, Duration.ofMinutes(10));
        final WxMaUserInfo userInfo = wxMaService.getUserService().getUserInfo(sessionKey, encryptedData, ivStr);
        final User user = User.builder()
                .userId(userId)
                .nickname(userInfo.getNickName())
                .avatarUrl(userInfo.getAvatarUrl())
                .build();
        return user.updateById() ? Result.success() : Result.failed();
    }
}
