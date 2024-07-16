package com.quiz.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * DeviceDto
 * </p>
 *
 * @author XGeorge
 * @since 2024/7/16 上午11:33
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "DeviceDto对象", description = "设备相关信息")
public class DeviceDto {

    @ApiModelProperty("安卓设备ID")
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

    @ApiModelProperty("要添加到结果中的单位数量，可能是负数")
    private Long amountToAdd;

    @ApiModelProperty("要添加的时间单位，不为空")
    private String amountUnit;

    @Override
    public String toString() {
        return "DeviceDto{" +
                "deviceId='" + deviceId + '\'' +
                ", username='" + username + '\'' +
                ", endTime=" + endTime +
                ", lv=" + lv +
                ", amountToAdd=" + amountToAdd +
                ", amountUnit='" + amountUnit + '\'' +
                '}';
    }
}
