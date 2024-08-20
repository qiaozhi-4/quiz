package com.quiz.entity;

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
 * 设备卡密信息
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
@TableName("yz_device")
@ApiModel(value = "Device对象", description = "设备卡密信息")
public class Device extends Model<Device> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键,安卓设备ID")
    @TableId("device_id")
    private String deviceId;

    @ApiModelProperty("用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty("过期时间")
    @TableField("end_time")
    private LocalDateTime endTime;

    @ApiModelProperty("-1封禁,0试用,1vip,2svip,3max")
    @TableField("lv")
    private Integer lv;


    @Override
    public Serializable pkVal() {
        return this.deviceId;
    }

}
