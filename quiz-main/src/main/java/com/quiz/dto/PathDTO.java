package com.quiz.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * PathDTO
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/14 11:01
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "PathDTO对象", description = "请求路径信息以及把权限id替换为权限名称")
public class PathDTO implements Serializable {
    @ApiModelProperty("主键，路径ID。")
    @TableId(value = "path_id", type = IdType.AUTO)
    private Integer pathId;

    @ApiModelProperty("路径模式，如/api/users/**，/device/list/{current:d+}/{size:d+}。")
    @TableField("pattern")
    private String pattern;

    @ApiModelProperty("请求方式")
    @TableField("http_method")
    private String httpMethod;

    @ApiModelProperty("权限名称，如read、write。")
    @TableField("permission_name")
    private String permissionName;
}
