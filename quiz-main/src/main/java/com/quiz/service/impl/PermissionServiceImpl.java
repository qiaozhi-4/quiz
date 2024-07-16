package com.quiz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quiz.entity.Permission;
import com.quiz.mapper.PermissionMapper;
import com.quiz.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-07-16
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {
    @Override
    public Map<String, Object> paging(Integer current, Integer size) {
        //创建分页对象
        final Page<Permission> page = new Page<>(current, size);
        //创建查询条件
        final LambdaQueryWrapper<Permission> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        Page<Permission> devicePage = this.page(page, lambdaQueryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("records", devicePage.getRecords());
        hashMap.put("total", devicePage.getTotal());
        return hashMap;
    }
}
