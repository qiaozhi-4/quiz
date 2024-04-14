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
 * 权限表
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-14  0924:57:20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_permission")
@ApiModel(value = "TPermission对象", description = "权限表")
public class TPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键，权限ID。")
    @TableId(value = "permission_id", type = IdType.AUTO)
    private Integer permissionId;

    @ApiModelProperty("权限名称，如read、write。")
    @TableField("permission_name")
    private String permissionName;


}
