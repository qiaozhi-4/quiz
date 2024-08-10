package com.quiz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.dto.TaskDTO;
import com.quiz.entity.*;
import com.quiz.mapper.AnswersMapper;
import com.quiz.mapper.PaperMapper;
import com.quiz.mapper.PropMapper;
import com.quiz.mapper.TaskMapper;
import com.quiz.service.ITaskRecordService;
import com.quiz.service.ITaskService;
import com.quiz.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<TaskDTO> getAll(Integer userId) {
        /* 获取本周周一 */
        LocalDateTime mondayDateTime = LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).withHour(0).withMinute(0).withSecond(0).withNano(0);
        /* 获取本周任务完成情况 */
        List<TaskDTO> taskDTOS = this.baseMapper.selectTaskList(userId, mondayDateTime);
        /* 如果本周没有任务记录，则初始化 */
        if (taskDTOS.isEmpty()) {
            /* 获取本周的出题记录总数 */
            Long paperCount = paperMapper.selectCount(new LambdaQueryWrapper<Paper>().ge(Paper::getCreatedAt, mondayDateTime).eq(Paper::getCreatorUserId, userId));
            /* 获取本周的答题记录总数 */
            Long answersCount = answersMapper.selectCount(new LambdaQueryWrapper<Answers>().ge(Answers::getCreatedAt, mondayDateTime).eq(Answers::getResponderUserId, userId));
            /* 获取道具列表 */
            List<Prop> props = propMapper.selectList(null);
            /* 初始化任务记录 */
            List<TaskRecord> taskRecords = new ArrayList<>();
            taskDTOS = this.list().stream().map(e -> {
                TaskRecord taskRecord = TaskRecord.builder().taskId(e.getTaskId()).userId(userId).build();
                TaskDTO taskDTO = BeanUtils.copyProperties(e, TaskDTO.class);
                taskDTO.setIsReceiveAward(false);
                /* 设置奖励名称 */
                props.stream().filter(prop -> prop.getPropId().equals(e.getAwardId())).findFirst().ifPresent(prop -> taskDTO.setAwardName(prop.getPropName()));
                /* 判断任务是否完成,类型不同判断条件不同 */
                switch (taskDTO.getConditionType()) {
                    case 1:
                        taskDTO.setFinishNumber(paperCount > taskDTO.getConditionNumber() ? taskDTO.getConditionNumber() : paperCount.intValue());
                        taskRecord.setFinishNumber(taskDTO.getFinishNumber());
                        break;
                    case 2:
                        taskDTO.setFinishNumber(answersCount > taskDTO.getConditionNumber() ? taskDTO.getConditionNumber() : answersCount.intValue());
                        taskRecord.setFinishNumber(taskDTO.getFinishNumber());
                        break;
                }
                taskRecords.add(taskRecord);
                return taskDTO;
            }).collect(Collectors.toList());
            taskRecordService.saveBatch(taskRecords);
        }
        return taskDTOS;
    }
}
