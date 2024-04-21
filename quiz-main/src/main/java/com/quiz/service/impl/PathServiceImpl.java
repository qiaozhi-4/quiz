package com.quiz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.dto.PathDto;
import com.quiz.entity.Path;
import com.quiz.mapper.PathMapper;
import com.quiz.service.IPathService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
public class PathServiceImpl extends ServiceImpl<PathMapper, Path> implements IPathService {
    private final PathMapper pathMapper;

    @Override
    public List<PathDto> getPathDtoList() {
        return pathMapper.getPathDtoList();
    }
}
