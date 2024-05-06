package com.quiz.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.quiz.constant.Constants;
import com.quiz.dto.UserDto;
import com.quiz.entity.User;
import com.quiz.entity.UserAuth;
import com.quiz.entity.UserRoles;
import com.quiz.mapper.UserMapper;
import com.quiz.service.IWxUserService;
import com.quiz.utils.Assert;
import com.quiz.utils.JWTUtils;
import com.quiz.utils.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.data.redis.core.RedisTemplate;
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
public class WxUserServiceImpl implements IWxUserService {

    private final WxMaService wxMaService;
    private final UserMapper userMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Result<Object> login(String code) throws WxErrorException {
        final WxMaJscode2SessionResult sessionInfo = wxMaService.getUserService().getSessionInfo(code);
        User user = userMapper.selectUserByProviderId(sessionInfo.getOpenid());

        Map<Object, Object> map = new HashMap<>();
        if (Objects.isNull(user)) {
            log.info("当前用户第一次登录,openID:" + sessionInfo.getOpenid());
            /* 创建用户,并插入数据库 */
            user = UserDto.defUser();
            Assert.isTrue(user.insert(), "插入用户失败");
            /* 创建用户关联的第三方登录信息,并插入数据库 */
            Assert.isTrue(UserAuth.builder()
                            .userId(user.getUserId())
                            .provider(Constants.WX_NAME)
                            .providerId(sessionInfo.getOpenid())
                            .build()
                            .insert()
                    , "插入用户第三方登录信息失败");
            Assert.isTrue(UserRoles.builder()
                    .roleId(2).userId(user.getUserId())
                    .build().insert(), "插入用户角色信息失败");
        } else {
            user.setLastLoginAt(LocalDateTime.now());
            Assert.isTrue(user.updateById(), "更新用户上次登录时间失败");
        }
        final String jwtToken = JWTUtils.getJwtToken(user.getUserId().toString(), user.getUsername());
        map.put(JWTUtils.TOKEN_KEY, jwtToken);
        map.put("userInfo", user);
        return Result.success(map);
    }

    @Override
    public Boolean saveUserInfo(User user) {
        return user.updateById();
    }
}
