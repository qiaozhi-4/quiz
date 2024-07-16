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
 * 权限表
 * </p>
 *
 * @author XGeorge
 * @since 2024-07-16
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("t_permission")
@ApiModel(value = "Permission对象", description = "权限表")
public class Permission extends Model<Permission> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键，权限ID。")
    @TableId(value = "permission_id", type = IdType.AUTO)
    private Integer permissionId;

    @ApiModelProperty("权限名称，如read、write。")
    @TableField("permission_name")
    private String permissionName;

    @ApiModelProperty("描述")
    @TableField("describe_")
    private String describe;


    @Override
    public Serializable pkVal() {
        return this.permissionId;
    }

}
