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
 * 用户关联道具表
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-08
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("q_user_prop")
@ApiModel(value = "UserProp对象", description = "用户关联道具表")
public class UserProp extends Model<UserProp> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键，权限ID。")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户id。")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("道具id。")
    @TableField("prop_id")
    private Integer propId;

    @ApiModelProperty("道具数量")
    @TableField("number")
    private Integer number;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
