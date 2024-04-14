package com.quiz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-14  0924:57:20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_role")
@ApiModel(value = "TRole对象", description = "角色表")
public class TRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键，角色ID。")
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    @ApiModelProperty("角色名称，如ROLE_ADMIN、ROLE_USER。")
    @TableField("role_name")
    private String roleName;


}
