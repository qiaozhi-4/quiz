package com.quiz.mapper;

import com.quiz.entity.TPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-14  0924:57:20
 */
public interface TPermissionMapper extends BaseMapper<TPermission> {
    /**
     * 通过用户id获取用户所有权限
     * @param userId 用户id
     * @return 权限集合
     */
    List<TPermission> getUserPermissionsByUserId(Integer userId);
}
