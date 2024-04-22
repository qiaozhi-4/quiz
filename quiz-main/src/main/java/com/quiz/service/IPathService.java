package com.quiz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quiz.dto.PathDto;
import com.quiz.entity.Path;

import java.util.List;

/**
 * <p>
 * 路径表 服务类
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-14  0924:57:20
 */
public interface IPathService extends IService<Path> {
    /**
     * 获取所有权限信息,这个dto把权限id替换为权限名称
     */
    List<PathDto> getPathDtoList();
}
