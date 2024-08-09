package com.quiz.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quiz.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * UserDTO
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/17 22:20
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "UserDto对象", description = "用户基本信息,添加权限列表")
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键，用户ID。")
    private Integer userId;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("头像地址")
    private String avatarUrl;

    @ApiModelProperty("用户账号")
    private String username;

    /**
     * 在序列化时忽略
     */
    @JsonIgnore
    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String mail;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedAt;

    @ApiModelProperty("用户上次登录时间")
    private LocalDateTime lastLoginAt;

    @ApiModelProperty("账号是否启用:0=正常,-1=禁用")
    private Long enabled;

    @ApiModelProperty("用户权限集合")
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
