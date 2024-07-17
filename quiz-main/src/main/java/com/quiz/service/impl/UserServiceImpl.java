package com.quiz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.dto.UserDto;
import com.quiz.entity.User;
import com.quiz.entity.UserAuth;
import com.quiz.entity.UserRoles;
import com.quiz.mapper.UserMapper;
import com.quiz.service.IUserService;
import com.quiz.utils.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-14  0924:57:20
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "user")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Cacheable(key = "#account", unless = "#result == null")
    @Override
    public UserDto getUserDtoByAccount(String account) {
        Assert.isNotNull(account, "账户不能为空");
        // 用户名必须是唯一的，不允许重复
        final UserDto userDto = this.baseMapper.selectUserDtoByAccount(account);
        Assert.isNotNull(userDto, "根据账户找不到该用户的信息");
        return userDto;
    }

    private void addUser(User user) {
        Assert.isTrue(user.insert(), "插入用户失败");
        Assert.isTrue(UserRoles.builder().roleId(2).userId(user.getUserId())
                .build().insert(), "插入用户角色信息失败");
    }

    @Override
    public User registerUser(User user) {
        Assert.isNotNull(user.getPassword(), "密码不能为空");
        this.addUser(user);
        return user;
    }

    @Override
    public User registerUserTP(String tPName, String providerId) {
        User user = UserDto.defUser();
        this.addUser(user);
        Assert.isTrue(UserAuth.builder()
                        .userId(user.getUserId())
                        .provider(tPName)
                        .providerId(providerId)
                        .build()
                        .insert()
                , "插入用户第三方登录信息失败");
        return user;
    }
}
