package com.quiz.security.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.quiz.dto.UserDto;
import com.quiz.service.ITUserService;
import com.quiz.utils.JWTUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * JwtAuthenticationTokenFilter
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/12 12:56
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    private ITUserService userService;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        // 获取到当前用户的account
        String account = JWTUtils.getMemberAccountByJwtToken(request);
        // 判断账户是否为空; 判断 SecurityContext 是否有身份
        if (!StringUtils.isEmpty(account) && SecurityContextHolder.getContext().getAuthentication() == null) {
            // 获取用户信息以及权限
            final UserDto userDto = userService.getUserByAccount(account);
            final UserDetails user = User.builder()
                    .password(userDto.getUsername())
                    .username(userDto.getUsername())
                    .authorities(userDto.getPermissions().toArray(new String[0]))
                    .build();
            // 生成用户权限认证
            final UsernamePasswordAuthenticationToken authenticated =
                    UsernamePasswordAuthenticationToken.authenticated(user, null, user.getAuthorities());
            // 设置用户来源
            authenticated.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // 设置 SecurityContext 中的身份
            SecurityContextHolder.getContext().setAuthentication(authenticated);
        }
        filterChain.doFilter(request, response);
    }
}
