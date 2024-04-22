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
 * 角色权限关联表
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
@TableName("t_role_permissions")
@ApiModel(value = "RolePermissions对象", description = "角色权限关联表")
public class RolePermissions extends Model<RolePermissions> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("关联角色ID。")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty("关联权限ID。")
    @TableField("permission_id")
    private Integer permissionId;


    @Override
    public Serializable pkVal() {
        return null;
    }

}
