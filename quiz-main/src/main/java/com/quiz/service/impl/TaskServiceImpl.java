package com.quiz.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.dto.TaskDTO;
import com.quiz.entity.Task;
import com.quiz.entity.TaskRecord;
import com.quiz.mapper.AnswersMapper;
import com.quiz.mapper.PaperMapper;
import com.quiz.mapper.PropMapper;
import com.quiz.mapper.TaskMapper;
import com.quiz.service.ITaskRecordService;
import com.quiz.service.ITaskService;
import com.quiz.service.IUserPropService;
import com.quiz.utils.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 任务数据 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-10
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {
    private final PaperMapper paperMapper;
    private final AnswersMapper answersMapper;
    private final PropMapper propMapper;
    private final ITaskRecordService taskRecordService;
    private final IUserPropService userPropService;

    @Override
    public List<TaskDTO> getAllTaskAndUpdateOrSaveTaskRecord(Integer userId) {
        /* 获取本周周一 */
        LocalDateTime mondayDateTime = LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).withHour(0).withMinute(0).withSecond(0).withNano(0);
        /* 获取本周任务完成情况 */
        List<TaskDTO> taskDTOS = this.baseMapper.selectTaskList(userId, mondayDateTime);
        /* 如果本周没有任务记录，则初始化 */
        if (taskDTOS.isEmpty()) {
            List<TaskRecord> taskRecords = this.list().stream().map(task ->
                    TaskRecord.builder().taskId(task.getTaskId()).userId(userId).build()
            ).collect(Collectors.toList());
            taskRecordService.saveBatch(taskRecords);
            taskDTOS = this.baseMapper.selectTaskList(userId, mondayDateTime);
        }
        /* 更新本周的答题记录总数 */
        taskDTOS.forEach(taskDTO ->
                taskRecordService.update(new LambdaUpdateWrapper<TaskRecord>().ge(TaskRecord::getCreatedAt, mondayDateTime)
                        .eq(TaskRecord::getUserId, userId).eq(TaskRecord::getTaskId, taskDTO.getTaskId())
                        .set(TaskRecord::getFinishNumber, taskDTO.getFinishNumber()))
        );
        return taskDTOS;
    }

    @Override
    public TaskDTO finishTask(Integer userId, Integer taskId) {
        /* 获取本周周一 */
        LocalDateTime mondayDateTime = LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).withHour(0).withMinute(0).withSecond(0).withNano(0);
        TaskDTO taskDTO = this.baseMapper.selectTask(userId, taskId, mondayDateTime);
        Assert.isTrue(!taskDTO.getIsReceiveAward(), "任务奖励以领取");
        /* 判断任务是否完成 */
        Assert.isTrue(Objects.equals(taskDTO.getFinishNumber(), taskDTO.getConditionNumber()), "任务未完成");
        /* 领取奖励 */
        userPropService.gainProp(userId, taskDTO.getAwardId(), taskDTO.getAwardNumber());
        taskDTO.setIsReceiveAward(true);
        /* 更新任务记录 */
        taskRecordService.update(new LambdaUpdateWrapper<TaskRecord>().eq(TaskRecord::getTaskId, taskId).eq(TaskRecord::getUserId, userId)
                .set(TaskRecord::getIsReceiveAward, true)
                .set(TaskRecord::getUpdatedAt, LocalDateTime.now()));
        return taskDTO;
    }
}
