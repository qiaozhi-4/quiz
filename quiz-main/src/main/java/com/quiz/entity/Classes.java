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
 * 试卷分类
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
@TableName("q_classes")
@ApiModel(value = "Classes对象", description = "试卷分类")
public class Classes extends Model<Classes> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("唯一id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("类别id")
    @TableField("class_id")
    private Integer classId;

    @ApiModelProperty("类别名称")
    @TableField("class_name")
    private String className;

    @ApiModelProperty("类别描述")
    @TableField("class_describe")
    private String classDescribe;

    @ApiModelProperty("子类别id")
    @TableField("sub_class_id")
    private Integer subClassId;

    @ApiModelProperty("子类别名称")
    @TableField("sub_class_name")
    private String subClassName;

    @ApiModelProperty("子类别描述")
    @TableField("sub_class_describe")
    private String subClassDescribe;

    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @ApiModelProperty("修改时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
