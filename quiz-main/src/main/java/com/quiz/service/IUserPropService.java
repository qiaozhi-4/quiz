package com.quiz.service;

import com.quiz.entity.UserProp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户关联道具表 服务类
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-08
 */
public interface IUserPropService extends IService<UserProp> {
    /**
     * 根据用户id和道具id获取用户道具信息
     *
     * @param userId 用户id
     * @param propId 道具id
     */
    UserProp getUserPropByUserIdAndPropId(Integer userId, Integer propId);

    /**
     * 设置道具
     *
     * @param userId 用户id
     * @param propId 道具id
     * @param num    数量
     */
    UserProp setProp(Integer userId, Integer propId, Integer num);

    /**
     * 使用道具
     *
     * @param userId 用户id
     * @param propId 道具id
     * @param num    使用数量
     */
    UserProp useProp(Integer userId, Integer propId, Integer num);

    /**
     * 获得道具
     *
     * @param userId 用户id
     * @param propId 道具id
     * @param num    添加数量
     */
    UserProp gainProp(Integer userId, Integer propId, Integer num);
}
