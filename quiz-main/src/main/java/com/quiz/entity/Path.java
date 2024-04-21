package com.quiz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 路径表
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-21  0624:56:28
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("t_path")
@ApiModel(value = "Path对象", description = "路径表")
public class Path extends Model<Path> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键，路径ID。")
    @TableId(value = "path_id", type = IdType.AUTO)
    private Integer pathId;

    @ApiModelProperty("路径模式，如/api/users/**，/device/list/{current:d+}/{size:d+}。")
    @TableField("pattern")
    private String pattern;

    @ApiModelProperty("请求方式")
    @TableField("http_method")
    private String httpMethod;

    @ApiModelProperty("关联权限ID。")
    @TableField("permission_id")
    private String permissionId;


    @Override
    public Serializable pkVal() {
        return this.pathId;
    }

}
