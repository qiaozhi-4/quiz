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
 * 题目表
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-21  1024:23:35
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("t_question")
@ApiModel(value = "Question对象", description = "题目表")
public class Question extends Model<Question> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("唯一主键")
    @TableId(value = "question_id", type = IdType.AUTO)
    private Integer questionId;

    @ApiModelProperty("类型代码")
    @TableField("class_code")
    private Integer classCode;

    @ApiModelProperty("题目")
    @TableField("title")
    private String title;

    @ApiModelProperty("选项")
    @TableField("options_")
    private String options;

    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @ApiModelProperty("修改时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;


    @Override
    public Serializable pkVal() {
        return this.questionId;
    }

}
