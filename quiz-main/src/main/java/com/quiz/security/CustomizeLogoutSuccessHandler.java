package com.quiz.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.utils.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * <p>
 * CustomizeLogoutSuccessHandler 退出登录成功
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/6 21:25
 */
@Component
public class CustomizeLogoutSuccessHandler implements LogoutSuccessHandler {
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Result<Object> result = Objects.isNull(authentication) ?
                Result.failed("未登录") : Result.success("退出登录成功");
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
