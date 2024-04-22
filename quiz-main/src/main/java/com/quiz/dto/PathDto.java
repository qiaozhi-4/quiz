package com.quiz.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.quiz.entity.Path;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
@ApiModel(value = "PathDto对象", description = "请求路径信息以及把权限id替换为权限名称")
public class PathDto extends Path {

    @ApiModelProperty("权限名称，如read、write。")
    @TableField("permission_name")
    private String permissionName;
}
