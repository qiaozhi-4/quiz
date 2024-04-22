package com.quiz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
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
 * @since 2024-04-22  0124:43:56
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("question_selects")
@ApiModel(value = "QuestionSelects对象", description = "题目答卷")
public class QuestionSelects extends Model<QuestionSelects> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("唯一主键")
    @TableId(value = "answer_id", type = IdType.AUTO)
    private Integer answerId;

    @ApiModelProperty("关联试卷id")
    @TableField("paper_id")
    private Integer paperId;

    @ApiModelProperty("答题用户id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("选择下标集合,以\",\"分割")
    @TableField("selects")
    private String selects;


    @Override
    public Serializable pkVal() {
        return this.answerId;
    }

}
