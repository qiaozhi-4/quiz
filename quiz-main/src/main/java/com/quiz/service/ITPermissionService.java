package com.quiz.service;

import com.quiz.entity.TPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-14  0924:57:20
 */
public interface ITPermissionService extends IService<TPermission> {
    /**
     * 分页获取
     *
     * @param current 第几页
     * @param size    每页多少条
     * @return Map { records: 数据, total: 总条数 }
     */
    Map<String, Object> paging(Integer current, Integer size);
}
