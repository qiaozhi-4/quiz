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
 * 第三方登录表
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-14  0924:57:20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_user_auth")
@ApiModel(value = "TUserAuth对象", description = "第三方登录表")
public class TUserAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键，ID。")
    @TableId(value = "auth_id", type = IdType.AUTO)
    private Integer authId;

    @ApiModelProperty("用户ID。")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("第三方服务的名称（如Google、Facebook等）。")
    @TableField("provider")
    private String provider;

    @ApiModelProperty("第三方提供的唯一标识符。比如微信的openId")
    @TableField("provider_id")
    private String providerId;


}
