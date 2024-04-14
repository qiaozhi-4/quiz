package com.quiz.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.quiz.dto.PathDTO;
import com.quiz.entity.TPath;
import com.quiz.mapper.TPathMapper;
import com.quiz.service.ITPathService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 路径表 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-14  0924:57:20
 */
@Service
@RequiredArgsConstructor
public class TPathServiceImpl extends ServiceImpl<TPathMapper, TPath> implements ITPathService {
    // 路径匹配器
    private final AntPathMatcher antPathMatcher;
    private final TPathMapper pathMapper;

    @Override
    public List<PathDTO> getPathDTOs() {
        return pathMapper.getPathDTOs();
    }

    @Override
    public String getPermissionNameByPath(String requestPath) {
        final List<PathDTO> pathDTOs = getPathDTOs();
        final Optional<PathDTO> pathOptional = pathDTOs.stream()
                .filter(path -> antPathMatcher.match(path.getPattern(), requestPath))
                .findFirst();
        return pathOptional.isPresent() ? pathOptional.get().getPermissionName() : "";
    }
}
