package com.quiz.service.impl;

import com.quiz.entity.Role;
import com.quiz.mapper.RoleMapper;
import com.quiz.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-07-16
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
