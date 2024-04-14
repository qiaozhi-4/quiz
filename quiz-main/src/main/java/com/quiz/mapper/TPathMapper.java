package com.quiz.mapper;

import com.quiz.dto.PathDTO;
import com.quiz.entity.TPath;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 路径表 Mapper 接口
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-14  0924:57:20
 */
public interface TPathMapper extends BaseMapper<TPath> {
    /**
     * <p>
     * 获取所有权限信息,这个dto把权限id替换为权限名称
     * </p>
     *
     * @return 所有权限信息
     */
    List<PathDTO> getPathDTOs();
}
