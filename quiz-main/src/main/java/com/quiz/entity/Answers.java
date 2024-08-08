package com.quiz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 题目答卷
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-08
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("q_answers")
@ApiModel(value = "Answers对象", description = "题目答卷")
public class Answers extends Model<Answers> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("唯一主键")
    @TableId(value = "answer_id", type = IdType.AUTO)
    private Integer answerId;

    @ApiModelProperty("关联试卷id")
    @TableField("paper_id")
    private Integer paperId;

    @ApiModelProperty("答题用户id")
    @TableField("responder_user_id")
    private Integer responderUserId;

    @ApiModelProperty("选择下标集合,以@@分隔")
    @TableField("selects")
    private String selects;

    @ApiModelProperty("得分")
    @TableField("score")
    private Integer score;

    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;


    @Override
    public Serializable pkVal() {
        return this.answerId;
    }

}
