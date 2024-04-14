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
 * 路径表
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-14  0924:57:20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_path")
@ApiModel(value = "TPath对象", description = "路径表")
public class TPath implements Serializable {

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


}
