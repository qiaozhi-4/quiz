package com.quiz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.entity.PaperTags;
import com.quiz.mapper.PaperTagsMapper;
import com.quiz.service.IPaperTagsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 试卷关联标签 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-24
 */
@Service
public class PaperTagsServiceImpl extends ServiceImpl<PaperTagsMapper, PaperTags> implements IPaperTagsService {

}
