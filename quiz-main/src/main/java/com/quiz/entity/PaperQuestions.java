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
 * 试卷/答题关联题目
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
@TableName("q_paper_questions")
@ApiModel(value = "PaperQuestions对象", description = "试卷/答题关联题目")
public class PaperQuestions extends Model<PaperQuestions> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "pq_id", type = IdType.AUTO)
    private Integer pqId;

    @ApiModelProperty("试卷ID")
    @TableField("paper_id")
    private Integer paperId;

    @ApiModelProperty("题目ID")
    @TableField("question_id")
    private Integer questionId;

    @ApiModelProperty("出题人选择下标")
    @TableField("pq_select_index")
    private Integer pqSelectIndex;

    @ApiModelProperty("出题人额外描述")
    @TableField("pq_extra_describe")
    private String pqExtraDescribe;

    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @ApiModelProperty("修改时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;


    @Override
    public Serializable pkVal() {
        return this.pqId;
    }

}
