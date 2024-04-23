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

/**
 * <p>
 * 题目答卷
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-23  0824:36:01
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

    @ApiModelProperty("选择下标集合,以\",\"分割")
    @TableField("selects")
    private String selects;


    @Override
    public Serializable pkVal() {
        return this.answerId;
    }

}
