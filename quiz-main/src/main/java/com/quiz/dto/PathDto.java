package com.quiz.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.quiz.entity.TPath;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * PathDto
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/14 11:01
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "PathDTO对象", description = "请求路径信息以及把权限id替换为权限名称")
public class PathDto extends TPath implements Serializable {

    @ApiModelProperty("权限名称，如read、write。")
    @TableField("permission_name")
    private String permissionName;
}