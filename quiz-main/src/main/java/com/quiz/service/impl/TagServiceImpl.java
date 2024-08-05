package com.quiz.service.impl;

import com.quiz.entity.Tag;
import com.quiz.mapper.TagMapper;
import com.quiz.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 题目标签 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-05
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

}
