package com.quiz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;
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
 * 脚本使用信息
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-20
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("yz_use_info")
@ApiModel(value = "UseInfo对象", description = "脚本使用信息")
public class UseInfo extends Model<UseInfo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键,使用功能记录id")
    @TableId(value = "use_id", type = IdType.AUTO)
    private Integer useId;

    @ApiModelProperty("卡号")
    @TableField("username")
    private String username;

    @ApiModelProperty("账号(游戏的)")
    @TableField("account_")
    private String account;

    @ApiModelProperty("密码(游戏的)")
    @TableField("password")
    private String password;

    @ApiModelProperty("调用的方法名称")
    @TableField("function_name")
    private String functionName;

    @ApiModelProperty("区服id")
    @TableField("server_id")
    private Integer serverId;

    @ApiModelProperty("区服名称")
    @TableField("server_name")
    private String serverName;

    @ApiModelProperty("勇者id")
    @TableField("brave_id")
    private Integer braveId;

    @ApiModelProperty("玩家key")
    @TableField("player_key")
    private String playerKey;

    @ApiModelProperty("玩家昵称")
    @TableField("player_name")
    private String playerName;

    @ApiModelProperty("玩家角色id")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty("玩家角色名称")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty("使用时间")
    @TableField("date_time")
    private LocalDateTime dateTime;


    @Override
    public Serializable pkVal() {
        return this.useId;
    }

}
