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
 * 题目关联标签
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
@TableName("q_question_tags")
@ApiModel(value = "QuestionTags对象", description = "题目关联标签")
public class QuestionTags extends Model<QuestionTags> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("题目ID")
    @TableField("question_id")
    private Integer questionId;

    @ApiModelProperty("标签ID")
    @TableField("tag_id")
    private Integer tagId;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
