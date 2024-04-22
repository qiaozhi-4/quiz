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
 * 题目类别
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
@TableName("m_question_class")
@ApiModel(value = "QuestionClass对象", description = "题目类别")
public class QuestionClass extends Model<QuestionClass> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("唯一主键")
    @TableId(value = "class_id", type = IdType.AUTO)
    private Integer classId;

    @ApiModelProperty("类型代码")
    @TableField("class_code")
    private Integer classCode;

    @ApiModelProperty("类型名称")
    @TableField("class_name")
    private String className;


    @Override
    public Serializable pkVal() {
        return this.classId;
    }

}
