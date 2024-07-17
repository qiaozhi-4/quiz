package com.quiz.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.dto.UserDto;
import com.quiz.service.IUserService;
import com.quiz.utils.Assert;
import com.quiz.utils.JWTUtils;
import com.quiz.utils.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

/**
 * <p>
 * CustomizeAuthenticationSuccessHandler 登录成功
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/6 19:39
 */
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private IUserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // 更新用户表上次登录时间
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDto user = userService.getUserDtoByAccount(userDetails.getUsername());
        user.setLastLoginAt(LocalDateTime.now());
        Assert.isTrue(userService.updateById(user), "更新登录时间失败!");

        // 根据用户的 id 和 username 生成 token 并返回
        String jwtToken = JWTUtils.getJwtToken(user.getUserId().toString(), user.getUsername());
        Map<String, String> results = Collections.singletonMap(JWTUtils.TOKEN_KEY, jwtToken);

        Result<Map<String, String>> result = Result.success(results);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
