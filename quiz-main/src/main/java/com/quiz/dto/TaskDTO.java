package com.quiz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * TaskDTO
 * </p>
 *
 * @author XGeorge
 * @since 2024/8/10 下午3:51
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "TaskDTO对象", description = "任务数据")
public class TaskDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("任务ID")
    private Integer taskId;

    @ApiModelProperty("奖励id")
    private Integer awardId;

    @ApiModelProperty("奖励数量")
    private Integer awardNumber;

    @ApiModelProperty("任务描述")
    private String describe;

    @ApiModelProperty("任务条件类型,1:出题;2:答题")
    private Integer conditionType;

    @ApiModelProperty("任务条件计数")
    private Integer conditionNumber;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedAt;

    @ApiModelProperty("奖励名称")
    private String awardName;

    @ApiModelProperty("任务完成计数")
    private Integer finishNumber;

    @ApiModelProperty("是否领取奖励")
    private Boolean isReceiveAward;
}
