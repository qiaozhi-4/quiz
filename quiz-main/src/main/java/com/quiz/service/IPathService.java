package com.quiz.service;

import com.quiz.entity.Path;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 路径表 服务类
 * </p>
 *
 * @author XGeorge
 * @since 2024-07-16
 */
public interface IPathService extends IService<Path> {
    /**
     * 更新所有接口路径
     */
    List<Path> updateAllPath();

    /**
     * 获取所有接口路径
     */
    List<Path> getAllPath();
}
