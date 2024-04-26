package com.quiz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.entity.PaperClasses;
import com.quiz.mapper.PaperClassesMapper;
import com.quiz.service.IPaperClassesService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 试卷关联试卷分类 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-26
 */
@Service
public class PaperClassesServiceImpl extends ServiceImpl<PaperClassesMapper, PaperClasses> implements IPaperClassesService {

}
