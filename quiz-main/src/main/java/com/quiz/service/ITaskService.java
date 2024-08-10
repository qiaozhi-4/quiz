package com.quiz.service;

import com.quiz.dto.TaskDTO;
import com.quiz.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 任务数据 服务类
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-10
 */
public interface ITaskService extends IService<Task> {
    /**
     * 查询用户本周任务列表,如果本周任务未初始化,则插入,如果已初始化,则更新
     *
     * @param userId 用户id
     */
    List<TaskDTO> getAllTaskAndUpdateOrSaveTaskRecord(Integer userId);

    /**
     * 完成任务
     * @param userId 用户id
     * @param taskId 任务id
     */
    TaskDTO finishTask(Integer userId, Integer taskId);
}
