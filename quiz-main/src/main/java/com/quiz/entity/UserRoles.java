package com.quiz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 用户角色关联表
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
@TableName("user_roles")
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
