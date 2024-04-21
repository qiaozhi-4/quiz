package com.quiz.mapper;

import com.quiz.dto.UserDto;
import com.quiz.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-14  0924:57:20
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据账户获取用户信息
     *
     * @param account 用户名 or 邮箱 or 电话号码
     * @return 用户信息
     */
    UserDto selectUserDtoByAccount(String account);
}
