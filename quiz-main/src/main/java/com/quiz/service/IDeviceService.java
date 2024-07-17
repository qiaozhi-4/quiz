package com.quiz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quiz.entity.Device;

import java.util.Map;

/**
 * <p>
 * 设备卡密信息 服务类
 * </p>
 *
 * @author XGeorge
 * @since 2024-07-14
 */
public interface IDeviceService extends IService<Device> {

    /**
     * 分页获取
     *
     * @param current 第几页
     * @param size    每页多少条
     * @return Map { records: 数据, total: 总条数 }
     */
    Map<String, Object> paging(Integer current, Integer size);

    /**
     * 修改设备过期时间,设备等级..
     *
     * @param deviceId    设备id
     * @param amountToAdd 要添加到结果中的单位数量，可能是负数
     * @param unit        要添加的时间单位，不为空
     * @param level       设备等级
     */
    Device change(String deviceId, Long amountToAdd, String unit, Integer level);

    /**
     * 验证涉笔
     *
     * @param deviceId 设备Id
     * @param versions 脚本版本
     */
    Device verify(String deviceId, String versions);
}
