package com.quiz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.entity.Classes;
import com.quiz.mapper.ClassesMapper;
import com.quiz.service.IClassesService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 试卷分类 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-26
 */
@Service
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes> implements IClassesService {

}
