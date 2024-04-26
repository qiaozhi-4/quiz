package com.quiz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 试卷关联试卷分类
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-26
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("q_paper_classes")
@ApiModel(value = "PaperClasses对象", description = "试卷关联试卷分类")
public class PaperClasses extends Model<PaperClasses> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("试卷ID。")
    @TableField("paper_id")
    private Integer paperId;

    @ApiModelProperty("分类ID。")
    @TableField("class_id")
    private Integer classId;


    @Override
    public Serializable pkVal() {
        return null;
    }

}
