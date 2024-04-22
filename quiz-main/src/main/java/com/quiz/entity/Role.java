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
 * 角色表
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
@TableName("m_role")
@ApiModel(value = "Role对象", description = "角色表")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键，角色ID。")
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    @ApiModelProperty("角色名称，如ROLE_ADMIN、ROLE_USER。")
    @TableField("role_name")
    private String roleName;


    @Override
    public Serializable pkVal() {
        return this.roleId;
    }

}
