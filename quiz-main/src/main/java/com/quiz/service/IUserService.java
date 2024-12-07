package com.quiz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quiz.dto.UserDTO;
import com.quiz.entity.User;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-14  0924:57:20
 */
public interface IUserService extends IService<User> {
    /**
     * 根据账户获取用户信息
     *
     * @param account 用户名 or 邮箱 or 电话号码
     */
    UserDTO getUserDtoByAccount(String account);

    /**
     * 平台注册用户
     */
    User registerUser(User user);

    /**
     * 第三方注册用户
     *
     * @param tPName     第三方平台的名称
     * @param providerId 用户在第三方平台相对当前应用的唯一id
     */
    User registerUserTP(String tPName, String providerId);

    /**
     * 根据id获取用户信息
     *
     * @param userId 用户id
     */
    User getUserById(Integer userId);

    /**
     * 获取亲密排行榜数据
     *
     * @param userId 用户id
     */
    List<UserDTO> getIntimateRanking(Integer userId);

      /**
     * 根据用户id获取用户信息
     *
     * @param userId 用户id
     */
    UserDTO getUserDtoByUserId(Integer userId);
}
