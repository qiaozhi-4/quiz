package com.quiz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.entity.Tag;
import com.quiz.mapper.TagMapper;
import com.quiz.service.ITagService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 试卷标签 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-24
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

}
