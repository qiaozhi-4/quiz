package com.quiz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quiz.dto.PathDto;
import com.quiz.entity.Path;

import java.util.List;

/**
 * <p>
 * 路径表 Mapper 接口
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-23  0224:01:42
 */
public interface PathMapper extends BaseMapper<Path> {
    /**
     * 获取所有权限信息,这个dto添加权限名称
     */
    List<PathDto> selectPathDtoList();
}
