package com.quiz.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.enumerate.ResultEnum;
import com.quiz.utils.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * CustomizeAccessDeniedHandler 用户权限不足
 * 该类用来统一处理 AccessDeniedException 异常
 * 处理访问被拒绝失败。
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/6 18:14
 */
@Component
public class CustomizeAccessDeniedHandler implements AccessDeniedHandler {
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        final Result<Object> result = Result.instance(ResultEnum.FAILED_NO_PERMISSION);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
