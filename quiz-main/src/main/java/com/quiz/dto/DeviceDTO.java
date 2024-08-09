package com.quiz.dto;

import com.quiz.entity.Device;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * DeviceDTO
 * </p>
 *
 * @author XGeorge
 * @since 2024/7/16 上午11:33
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "DeviceDto对象", description = "设备相关信息")
public class DeviceDTO extends Device {

    @ApiModelProperty("要添加到结果中的单位数量，可能是负数")
    private Long amountToAdd;

    @ApiModelProperty("要添加的时间单位，不为空")
    private String amountUnit;

    @Override
    public String toString() {
        return "DeviceDTO{" +
                "deviceId='" + getDeviceId() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", endTime=" + getEndTime() +
                ", lv=" + getLv() +
                ", amountToAdd=" + amountToAdd +
                ", amountUnit='" + amountUnit + '\'' +
                '}';
    }
}
