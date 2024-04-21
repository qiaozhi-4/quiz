package com.quiz.mapper;

import com.quiz.entity.Permission;
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
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 通过用户id获取用户所有权限
     * @param userId 用户id
     * @return 权限集合
     */
    List<Permission> getUserPermissionsByUserId(Integer userId);
}
