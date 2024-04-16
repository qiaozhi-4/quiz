package com.quiz.service;

import com.quiz.dto.PathDto;
import com.quiz.entity.TPath;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 路径表 服务类
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-14  0924:57:20
 */
public interface ITPathService extends IService<TPath> {
    /**
     * <p>
     * 获取所有权限信息,这个dto把权限id替换为权限名称
     * </p>
     *
     * @return 所有权限信息
     */
    List<PathDto> getPathDtoList();
}
