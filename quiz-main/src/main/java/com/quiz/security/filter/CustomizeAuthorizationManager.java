package com.quiz.security.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.quiz.entity.Path;
import com.quiz.service.IPathService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * <p>
 * CustomizeAuthorizationManager 验证用户是否有权限访问相关资源
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/11 16:31
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class CustomizeAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    private final IPathService pathService;
    private final AntPathMatcher antPathMatcher;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        String requestPath = context.getRequest().getRequestURI();
        final List<Path> pathDtoList = pathService.list();
        final Optional<Path> pathOptional = pathDtoList.stream()
                .filter(path -> antPathMatcher.match(path.getPattern(), requestPath))
                .findFirst();
        if (!pathOptional.isPresent() || StringUtils.isBlank(pathOptional.get().getPermissionName())) {
            log.debug("[{}]:不存在数据库,或者所需权限为空!", requestPath);
            return new AuthorizationDecision(true);
        }
        return new AuthorizationDecision(authentication.get().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(authenticationPermission ->
                        authenticationPermission.equals(pathOptional.get().getPermissionName())));
    }
}
