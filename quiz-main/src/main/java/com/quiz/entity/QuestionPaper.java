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
 * 题目试卷
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
@TableName("question_paper")
@ApiModel(value = "QuestionPaper对象", description = "题目试卷")
public class QuestionPaper extends Model<QuestionPaper> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("唯一主键")
    @TableId(value = "paper_id", type = IdType.AUTO)
    private Integer paperId;

    @ApiModelProperty("出题用户id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("题目id集合,以\",\"分割")
    @TableField("questions")
    private String questions;

    @ApiModelProperty("答案下标集合,以\",\"分割")
    @TableField("answers")
    private String answers;


    @Override
    public Serializable pkVal() {
        return this.paperId;
    }

}
