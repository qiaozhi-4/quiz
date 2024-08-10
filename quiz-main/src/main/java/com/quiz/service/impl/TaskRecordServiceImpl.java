package com.quiz.service.impl;

import com.quiz.entity.TaskRecord;
import com.quiz.mapper.TaskRecordMapper;
import com.quiz.service.ITaskRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务记录 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-10
 */
@Service
public class TaskRecordServiceImpl extends ServiceImpl<TaskRecordMapper, TaskRecord> implements ITaskRecordService {

}
