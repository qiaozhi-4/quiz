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
 * 道具表
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-20
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("q_prop")
@ApiModel(value = "Prop对象", description = "道具表")
public class Prop extends Model<Prop> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键,道具id")
    @TableId(value = "prop_id", type = IdType.AUTO)
    private Integer propId;

    @ApiModelProperty("道具名称")
    @TableField("prop_name")
    private String propName;

    @ApiModelProperty("道具描述")
    @TableField("prop_describe")
    private String propDescribe;


    @Override
    public Serializable pkVal() {
        return this.propId;
    }

}
