package com.quiz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quiz.dto.TaskDTO;
import com.quiz.entity.Task;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 任务数据 Mapper 接口
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-21
 */
public interface TaskMapper extends BaseMapper<Task> {
    /**
     * 查询用户本周任务列表
     *
     * @param userId         用户id
     * @param earliestDateTime 最早时间
     */
    List<TaskDTO> selectTaskList(@Param("userId") Integer userId, @Param("earliestDateTime") LocalDateTime earliestDateTime);
    /**
     * 查询用户本周任务列表
     *
     * @param taskId         任务id
     * @param userId         用户id
     * @param earliestDateTime 最早时间
     */
    TaskDTO selectTask(@Param("userId") Integer userId, @Param("taskId") Integer taskId, @Param("earliestDateTime") LocalDateTime earliestDateTime);
}
