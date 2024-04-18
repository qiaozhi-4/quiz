package com.quiz.security.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.quiz.dto.UserDto;
import com.quiz.security.service.ISecurityUserService;
import com.quiz.service.ITUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据账号获取用信息
        UserDto user = userService.getUserByAccount(username);

        // 如果走了缓存,就重新获取密码
        if (StringUtils.isBlank(user.getPassword()))
            user.setPassword(userService.getById(user.getUserId()).getPassword());

        return User.builder()
                .password(user.getPassword())
                .username(user.getUsername())
                .accountLocked(user.getEnabled() < 0)
                .authorities(user.getPermissions().toArray(new String[0]))
                .build();
    }
}
