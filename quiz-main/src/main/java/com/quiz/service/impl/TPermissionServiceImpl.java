package com.quiz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quiz.entity.TPermission;
import com.quiz.mapper.TPermissionMapper;
import com.quiz.service.ITPermissionService;
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
 * @since 2024-04-14  0924:57:20
 */
@Service
public class TPermissionServiceImpl extends ServiceImpl<TPermissionMapper, TPermission> implements ITPermissionService {
    @Override
    public Map<String, Object> paging(Integer current, Integer size) {
        //创建分页对象
        final Page<TPermission> page = new Page<>(current, size);
        //创建查询条件
        final LambdaQueryWrapper<TPermission> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        Page<TPermission> devicePage = this.page(page, lambdaQueryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("records", devicePage.getRecords());
        hashMap.put("total", devicePage.getTotal());
        return hashMap;
    }
}
