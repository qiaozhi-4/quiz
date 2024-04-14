package com.quiz.security.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.quiz.entity.TPath;
import com.quiz.entity.TPermission;
import com.quiz.service.ITPathService;
import com.quiz.service.ITPermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

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
    // 路径匹配器
    private final AntPathMatcher antPathMatcher;
    private final ITPathService pathService;
    private final ITPermissionService permissionService;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        String requestPath = context.getRequest().getRequestURI();
        final Optional<TPath> pathOptional = pathService.list().stream()
                .filter(path -> antPathMatcher.match(path.getPattern(), requestPath))
                .findFirst();
        if (!pathOptional.isPresent() || StringUtils.isBlank(pathOptional.get().getPermissionId())) {
            log.debug("[" + requestPath + "]:不存在数据库,或者所需权限为空!");
            return new AuthorizationDecision(true);
        }
        final TPermission permission = permissionService.getById(pathOptional.get().getPermissionId());

        return new AuthorizationDecision(authentication.get().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(authenticationPermission ->
                        authenticationPermission.equals(permission.getPermissionName())));
    }
}
