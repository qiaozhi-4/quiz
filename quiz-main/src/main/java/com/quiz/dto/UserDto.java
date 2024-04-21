package com.quiz.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.quiz.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * UserDto
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/17 22:20
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "UserDto对象", description = "用户基本信息,添加权限列表")
public class UserDto extends User implements Serializable {
    @ApiModelProperty("用户权限集合")
    @TableField("permissions")
    private List<String> permissions;
    public void setPermissionsStr(String permissions) {
        this.permissions = Arrays.asList(permissions.split(","));
    }
}
