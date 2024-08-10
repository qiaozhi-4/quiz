package com.quiz.service.impl;

import com.quiz.entity.Task;
import com.quiz.mapper.TaskMapper;
import com.quiz.service.ITaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务数据 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-10
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

}
