package com.quiz.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.quiz.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
public class UserDto extends User {
    @ApiModelProperty("用户权限集合")
    @TableField("permissions")
    private List<String> permissions;

    /**
     * 生成随机 username
     */
    public static String generateRandomUsername() {
        return "user_" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }

    /**
     * 生成随机 username 的用户
     */
    public static User defUser() {
        return User.builder().username(generateRandomUsername()).createdAt(LocalDateTime.now()).build();
    }
}
