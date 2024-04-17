package com.quiz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-14  0924:57:20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_user")
@ApiModel(value = "TUser对象", description = "用户信息表")
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键，用户ID。")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty("用户昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty("头像地址")
    @TableField("avatar_url")
    private String avatarUrl;

    @ApiModelProperty("用户账号")
    @TableField("username")
    private String username;

    @JsonIgnore
    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("邮箱")
    @TableField("mail")
    private String mail;

    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @ApiModelProperty("更新时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @ApiModelProperty("用户上次登录时间")
    @TableField("last_login_at")
    private LocalDateTime lastLoginAt;

    @ApiModelProperty("账号是否启用:0=正常,-1=禁用")
    @TableField("enabled")
    private Long enabled;


}
