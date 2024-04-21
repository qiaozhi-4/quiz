package com.quiz.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.dto.UserDto;
import com.quiz.entity.User;
import com.quiz.exception.APIException;
import com.quiz.mapper.UserMapper;
import com.quiz.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public UserDto getUserDtoByAccount(String account) {
        if (StringUtils.isBlank(account)) {
            throw new APIException("账户不能为空");
        }
        // 用户名必须是唯一的，不允许重复
        final UserDto userDto = this.baseMapper.selectUserDtoByAccount(account);
        if (Objects.isNull(userDto)) {
            throw new APIException("根据账户找不到该用户的信息");
        }
        return userDto;
    }
}
