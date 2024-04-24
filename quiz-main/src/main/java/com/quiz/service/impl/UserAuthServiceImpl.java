package com.quiz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.entity.UserAuth;
import com.quiz.mapper.UserAuthMapper;
import com.quiz.service.IUserAuthService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 第三方登录表 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-24
 */
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements IUserAuthService {

}
