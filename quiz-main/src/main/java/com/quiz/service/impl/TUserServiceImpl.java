package com.quiz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.quiz.dto.UserDto;
import com.quiz.entity.TPermission;
import com.quiz.entity.TUser;
import com.quiz.exception.APIException;
import com.quiz.mapper.TPermissionMapper;
import com.quiz.mapper.TUserMapper;
import com.quiz.service.ITUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

    private final TPermissionMapper permissionMapper;

    @Override
    public UserDto getUserByAccount(String account) {
        if (StringUtils.isBlank(account)) {
            throw new APIException("账户不能为空");
        }
        // 用户名必须是唯一的，不允许重复
        TUser user = this.getOne(new LambdaQueryWrapper<TUser>().eq(TUser::getUsername, account)
                .or().eq(TUser::getMail, account).or().eq(TUser::getPhone, account));
        if (Objects.isNull(user)) {
            throw new APIException("根据账户找不到该用户的信息");
        }
        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(user, userDto);

        final List<String> permissions = permissionMapper.getUserPermissionsByUserId(user.getUserId())
                .stream().map(TPermission::getPermissionName).collect(Collectors.toList());
        userDto.setPermissions(permissions);
        return userDto;
    }
}
