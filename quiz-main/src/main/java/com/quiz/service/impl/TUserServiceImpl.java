package com.quiz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.quiz.entity.TUser;
import com.quiz.exception.APIException;
import com.quiz.mapper.TUserMapper;
import com.quiz.service.ITUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-14  0924:57:20
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

    @Override
    public TUser getUserByAccount(String account) {
        if (null == account || account.isEmpty()) {
            throw new APIException("账户不能为空");
        }
        // 用户名必须是唯一的，不允许重复
        TUser tUser = this.getOne(new LambdaQueryWrapper<TUser>().eq(TUser::getUsername, account)
                .or().eq(TUser::getMail, account).or().eq(TUser::getPhone, account));
        if (Objects.isNull(tUser)) {
            throw new APIException("根据账户找不到该用户的信息");
        }
        return tUser;
    }
}
