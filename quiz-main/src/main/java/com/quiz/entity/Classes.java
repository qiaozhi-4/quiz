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
 * 试卷分类
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
@TableName("q_classes")
@ApiModel(value = "Classes对象", description = "试卷分类")
public class Classes extends Model<Classes> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("唯一主键")
    @TableId(value = "class_id", type = IdType.AUTO)
    private Integer classId;

    @ApiModelProperty("出题用户id(哪个用户的类别)")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("类别名称")
    @TableField("class_name")
    private String className;

    @ApiModelProperty("类别排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @ApiModelProperty("修改时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;


    @Override
    public Serializable pkVal() {
        return this.classId;
    }

}
