package com.quiz.config;

import com.quiz.security.filter.JwtAuthenticationTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * <p>
 * SecurityConfig
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/6 16:32
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final LogoutSuccessHandler logoutSuccessHandler;
    private final AuthorizationManager<RequestAuthorizationContext> authorizationManager;

    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // 启用跨域资源共享(CORS)支持
                .cors()
                .and()
                // 禁用跨站请求伪造(CSRF)保护
                .csrf().disable()

                .authorizeHttpRequests(authorizeHttpRequests ->
                        // 自定义权限认证管理器[应用于任何请求]
                        authorizeHttpRequests.anyRequest().access(authorizationManager)
                )

                // 自定义过滤器,每次请求通过Token获取用户权限并写入SecurityContext
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)

                // 异常处理
                .exceptionHandling()
                // 用户未登录
                .authenticationEntryPoint(authenticationEntryPoint)
                // 权限不足
                .accessDeniedHandler(accessDeniedHandler)

                .and()
                .formLogin()
                .permitAll()
                // 登录失败
                .failureHandler(authenticationFailureHandler)
                // 登录成功
                .successHandler(authenticationSuccessHandler)

                .and()
                // 退出
                .logout()
                // 允许所有用户
                .permitAll()
                // 退出登录成功
                .logoutSuccessHandler(logoutSuccessHandler)
                // 登出之后删除cookie
                .deleteCookies("JSESSIONID")

                .and()
                .sessionManagement()
                // 不通过 Session 获取 SecurityContext 所以禁用,使用无状态 Session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //
        ;

        return http.build();
    }

    /**
     * 默认开启密码加密，前端传入的密码Security会在加密后和数据库中的密文进行比对，一致的话就登录成功
     * 所以必须提供一个加密对象，供security加密前端明文密码使用
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
