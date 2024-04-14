package com.quiz.service.impl;

import com.quiz.entity.TUserAuth;
import com.quiz.mapper.TUserAuthMapper;
import com.quiz.service.ITUserAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 第三方登录表 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-14  0924:57:20
 */
@Service
public class TUserAuthServiceImpl extends ServiceImpl<TUserAuthMapper, TUserAuth> implements ITUserAuthService {

}
