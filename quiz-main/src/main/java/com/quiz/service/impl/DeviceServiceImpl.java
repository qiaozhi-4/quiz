package com.quiz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.entity.Device;
import com.quiz.exception.APIException;
import com.quiz.mapper.DeviceMapper;
import com.quiz.service.IDeviceService;
import com.quiz.utils.Assert;
import com.quiz.utils.MD5Utils;
import com.quiz.utils.RC4Util;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * <p>
 * 设备卡密信息 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-07-14
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {

    @Override
    public Map<String, Object> paging(Integer current, Integer size) {
        //创建分页对象
        final Page<Device> page = new Page<>(current, size);
        //创建查询条件
        final LambdaQueryWrapper<Device> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //通过指定字段逆向排序
        lambdaQueryWrapper.orderByDesc(Device::getEndTime);
        //查询
        Page<Device> devicePage = this.page(page, lambdaQueryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("records", devicePage.getRecords());
        hashMap.put("total", devicePage.getTotal());
        return hashMap;
    }

    @Override
    public Boolean change(String deviceId, Long amountToAdd, String unit, Integer level) {
        Assert.isNotNull(deviceId, "设备ID不能为空");
        Assert.isNotNull(amountToAdd, "添加时间不能为空");
        Assert.isNotNull(unit, "时间单位不能为空");
        val device = this.getById(deviceId);
        Assert.isNotNull(device, "设备不存在");

        val now = LocalDateTime.now();
        /* 判断是否以过期,过期则先吧时间设置为当前时间 */
        if (device.getEndTime().isBefore(now)) {
            device.setEndTime(now);
        }
        val chronoUnit = ChronoUnit.valueOf(unit);
        /* 设置新的过期时间 */
        device.setEndTime(device.getEndTime().plus(amountToAdd, chronoUnit));
        /* 设置新等级 */
        device.setLv(level);

        return device.updateById();
    }

    @Override
    public Device verify(String deviceId, String versions) {
        //当前时间
        LocalDateTime now = LocalDateTime.now();

        //对参数进行解码
        String nowStr = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:"));
        deviceId = RC4Util.decodeText(deviceId, nowStr);

        //查询该设备信息
        Device device = this.getById(deviceId);

        if (Objects.isNull(device)) {//新用户
            //判断是否符合插入条件
            if (Pattern.matches("\\w{14,18}", deviceId)) {
                //生成新设备信息
                device = Device
                        .builder()
                        .deviceId(deviceId)
                        .username(MD5Utils.MD5Lower(deviceId, nowStr))
                        .endTime(now)
                        .lv(0)
                        .build();
                //插入新设备信息
                Assert.isTrue(device.insert(), "插入新设备信息失败");
                throw new APIException("第一次登录,请复制日志里面的username给作者, username:" + device.getUsername());
            } else {
                throw new APIException("垃圾东西你都要破?我日尼玛!!!");
            }
        } else if (device.getEndTime().isBefore(now)) {
            throw new APIException("当前设备已过期!!!");
        } else if (device.getLv() == -1) {
            throw new APIException("当前设备以封禁!!!");
        }
        device.setDeviceId(MD5Utils.MD5Lower(deviceId + nowStr + versions + device.getLv()));
        return device;
    }
}
