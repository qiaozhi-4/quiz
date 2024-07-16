package com.quiz.service;

import com.quiz.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author XGeorge
 * @since 2024-07-16
 */
public interface IPermissionService extends IService<Permission> {
    /**
     * 分页获取
     *
     * @param current 第几页
     * @param size    每页多少条
     * @return Map { records: 数据, total: 总条数 }
     */
    Map<String, Object> paging(Integer current, Integer size);
}
