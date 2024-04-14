package com.quiz.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.enumerate.ResultEnum;
import com.quiz.utils.Result;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * CustomizeAuthenticationFailureHandler 登录失败
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/6 19:13
 */
@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        Result<String> result;
        if (exception instanceof AccountExpiredException) {
            //账号过期
            result = Result.failed(ResultEnum.FAILED_USER_ACCOUNT_EXPIRED);
        } else if (exception instanceof BadCredentialsException) {
            //用户名或密码错误
            result = Result.failed(ResultEnum.FAILED_USER_CREDENTIALS_ERROR);
        } else if (exception instanceof CredentialsExpiredException) {
            //密码过期
            result = Result.failed(ResultEnum.FAILED_USER_CREDENTIALS_EXPIRED);
        } else if (exception instanceof DisabledException) {
            //账号不可用
            result = Result.failed(ResultEnum.FAILED_USER_ACCOUNT_DISABLE);
        } else if (exception instanceof LockedException) {
            //账号锁定
            result = Result.failed(ResultEnum.FAILED_USER_ACCOUNT_LOCKED);
        } else if (exception instanceof InternalAuthenticationServiceException) {
            //用户不存在
            result = Result.failed(ResultEnum.FAILED_USER_ACCOUNT_NOT_EXIST);
        } else {
            //其他错误
            result = Result.failed(ResultEnum.FAILED);
        }
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
