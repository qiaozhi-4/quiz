package com.quiz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 任务数据
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-10
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("q_task")
@ApiModel(value = "Task对象", description = "任务数据")
public class Task extends Model<Task> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("任务ID")
    @TableId(value = "task_id", type = IdType.AUTO)
    private Integer taskId;

    @ApiModelProperty("奖励id")
    @TableField("award_id")
    private Integer awardId;

    @ApiModelProperty("奖励数量")
    @TableField("award_number")
    private Integer awardNumber;

    @ApiModelProperty("任务描述")
    @TableField("describe_")
    private String describe;

    @ApiModelProperty("任务条件类型,1:答题;2:出题")
    @TableField("condition_type")
    private Integer conditionType;

    @ApiModelProperty("任务条件计数")
    @TableField("condition_number")
    private Integer conditionNumber;

    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @ApiModelProperty("更新时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;


    @Override
    public Serializable pkVal() {
        return this.taskId;
    }

}
