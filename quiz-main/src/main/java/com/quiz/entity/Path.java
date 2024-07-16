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
 * 路径表
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

    @ApiModelProperty("HTTP 方法,如 POST, GET")
    @TableField("http_method")
    private String httpMethod;

    @ApiModelProperty("路径描述。")
    @TableField("describe_")
    private String describe;

    @ApiModelProperty("路径权限名称")
    @TableField("permission_name")
    private String permissionName;


    @Override
    public Serializable pkVal() {
        return this.pathId;
    }

}
