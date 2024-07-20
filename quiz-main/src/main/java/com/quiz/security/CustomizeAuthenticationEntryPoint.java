package com.quiz.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.enumerate.ResultEnum;
import com.quiz.utils.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * CustomizeAuthenticationEntryPoint 用户未登录
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/6 17:16
 */
@Component
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        Result<Object> result = Result.failed(ResultEnum.FAILED_USER_NOT_LOGIN);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
