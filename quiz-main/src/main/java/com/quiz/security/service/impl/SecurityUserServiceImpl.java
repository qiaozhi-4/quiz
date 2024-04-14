package com.quiz.security.service.impl;

import com.quiz.entity.TPermission;
import com.quiz.entity.TUser;
import com.quiz.mapper.TPermissionMapper;
import com.quiz.service.ITUserService;
import com.quiz.security.service.ISecurityUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * SecurityUserServiceImpl
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/10 16:38
 */
@Service
@RequiredArgsConstructor
public class SecurityUserServiceImpl implements ISecurityUserService {

    private final ITUserService userService;

    private final TPermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据账号获取用信息
        TUser user = userService.getUserByAccount(username);

        //根据用户id查询用户所有的权限
        List<TPermission> permissions = permissionMapper.getUserPermissionsByUserId(user.getUserId());

        //生成 security 的权限
        List<GrantedAuthority> grantedAuthorities = permissions.stream()
                .filter(permission -> !Objects.isNull(permission))//不知道为什么size会为1,但是元素为null,所以先过滤
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissionName())).collect(Collectors.toList());
        return new User(user.getUsername(), user.getPassword(), true, true
                , true, user.getEnabled() >= 0, grantedAuthorities);
    }
}
