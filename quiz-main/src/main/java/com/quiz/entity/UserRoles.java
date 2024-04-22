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
 * 用户角色关联表
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-22  1124:00:15
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("t_user_roles")
@ApiModel(value = "UserRoles对象", description = "用户角色关联表")
public class UserRoles extends Model<UserRoles> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("关联用户ID。")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("关联角色ID。")
    @TableField("role_id")
    private Integer roleId;


    @Override
    public Serializable pkVal() {
        return null;
    }

}
