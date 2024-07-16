package com.quiz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.annotation.PathPermission;
import com.quiz.constant.Constants;
import com.quiz.entity.Path;
import com.quiz.mapper.PathMapper;
import com.quiz.service.IPathService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 路径表 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-07-16
 */
@Service
@RequiredArgsConstructor
public class PathServiceImpl extends ServiceImpl<PathMapper, Path> implements IPathService {

    private final WebApplicationContext applicationContext;

    @Override
    public Boolean updateAllPath() {
        // 获取所有请求映射信息
        RequestMappingHandlerMapping mapping = applicationContext.getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
        // 拿到Handler适配器中的全部方法
        Map<RequestMappingInfo, HandlerMethod> methodMap = mapping.getHandlerMethods();
        final List<Path> paths = methodMap.entrySet().stream()
                // 过滤出controller层的方法
                .filter(handlerMethod -> handlerMethod.getValue().getBeanType().getName()
                        .matches("com\\.quiz\\.controller.*"))
                // 过滤出有ApiOperation注解的方法
                .filter(handlerMethod -> handlerMethod.getValue().getMethodAnnotation(ApiOperation.class) != null)
                .map(handlerMethod -> {
                    // 拿到请求方法
                    final String httpMethods = handlerMethod.getKey().getMethodsCondition().getMethods().stream()
                            .map(Enum::name).collect(Collectors.joining(Constants.SPACE_MARK));
                    assert handlerMethod.getKey().getPatternsCondition() != null;
                    // 拿到请求路径
                    final String patterns = String.join(Constants.SPACE_MARK, handlerMethod.getKey().getPatternsCondition().getPatterns());
                    // 拿到请求路径对应的ApiOperation注解
                    final ApiOperation apiOperation = handlerMethod.getValue().getMethodAnnotation(ApiOperation.class);
                    // 获取请求路径对应的描述
                    final String describe = apiOperation != null ? apiOperation.value() : null;
                    // 拿到请求路径对应的PathPermission注解
                    final PathPermission pathPermission = handlerMethod.getValue().getMethodAnnotation(PathPermission.class);
                    // 获取路径所需权限
                    final String permissionName = pathPermission != null ? pathPermission.value().getName() : null;
                    // 创建路径对象
                    return Path.builder()
                            .pattern(patterns)
                            .httpMethod(httpMethods)
                            .describe(describe)
                            .permissionName(permissionName).build();
                }).sorted(Comparator.comparing(Path::getPattern)).collect(Collectors.toList());
        // 删除所有路径信息
        this.remove(null);
        return this.saveBatch(paths);
    }
}
