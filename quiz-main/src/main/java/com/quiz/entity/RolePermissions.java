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
 * 角色权限关联表
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
@TableName("role_permissions")
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