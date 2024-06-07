package com.quiz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quiz.dto.UserDto;
import com.quiz.entity.User;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-23  0224:01:42
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据账户获取用户信息
     *
     * @param account 用户名 or 邮箱 or 电话号码
     */
    UserDto selectUserDtoByAccount(String account);

    /**
     * 根据第三方提供的唯一标识符查找对应用户
     *
     * @param providerId 第三方提供的唯一标识符。比如微信的openId
     */
    UserDto selectUserByProviderId(String providerId);
}
