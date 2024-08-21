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
 * 答题关联题目
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-21
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("q_answer_questions")
@ApiModel(value = "AnswerQuestions对象", description = "答题关联题目")
public class AnswerQuestions extends Model<AnswerQuestions> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "aq_id", type = IdType.AUTO)
    private Integer aqId;

    @ApiModelProperty("答卷id")
    @TableField("answer_id")
    private Integer answerId;

    @ApiModelProperty("题目ID")
    @TableField("question_id")
    private Integer questionId;

    @ApiModelProperty("答题人选择下标")
    @TableField("aq_select_index")
    private Integer aqSelectIndex;

    @ApiModelProperty("答题人额外描述")
    @TableField("aq_extra_describe")
    private String aqExtraDescribe;

    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @ApiModelProperty("修改时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;


    @Override
    public Serializable pkVal() {
        return this.aqId;
    }

}
