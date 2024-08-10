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
 * 试卷关联分类
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
@TableName("q_paper_classes")
@ApiModel(value = "PaperClasses对象", description = "试卷关联分类")
public class PaperClasses extends Model<PaperClasses> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("试卷ID")
    @TableField("paper_id")
    private Integer paperId;

    @ApiModelProperty("分类ID")
    @TableField("class_id")
    private Integer classId;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
