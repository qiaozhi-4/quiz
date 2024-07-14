package com.quiz.controller;


import com.quiz.entity.Device;
import com.quiz.service.IDeviceService;
import com.quiz.utils.Assert;
import com.quiz.utils.email.EmailUtil;
import com.quiz.utils.email.ToEmail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设备卡密信息 前端控制器
 * </p>
 *
 * @author XGeorge
 * @since 2024-07-14
 */
@RestController
@RequestMapping("/device")
@Api(tags = "设备卡密信息 前端控制器")
@RequiredArgsConstructor
public class DeviceController {
    private final IDeviceService deviceService;
    private final EmailUtil emailUtil;
    private final RedisTemplate<String, Object> redisTemplate;

    @GetMapping("list/{current:\\d+}/{size:\\d+}")
    @ApiOperation("分页获取数据集合、总条数")
    @ApiImplicitParams({ //参数说明
            @ApiImplicitParam(name = "current", value = "第几页", required = true, paramType = "path", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "size", value = "每页多少条", required = true, paramType = "path", dataTypeClass = Integer.class)
    })
    public Map<String, Object> list(@PathVariable("current") Integer current, @PathVariable("size") Integer size) {
        return deviceService.paging(current, size);
    }

    @PutMapping("update-device")
    @ApiOperation("更新设备信息,是否被封和过期时间")
    public Boolean updateDevice(String deviceId, Long amountToAdd, String unit, Integer level) {
        /*发送邮件*/
        emailUtil.commonEmail(ToEmail.builder().tos(new String[]{"468644641@qq.com"})
                .subject("设备修改")
                .content("本次修改的设备信息:\n" + String.format("deviceId: %s, amountToAdd: %d, unit: %s, level: %d", deviceId, amountToAdd, unit, level))
                .nickname("乔治")
                .build());
        return deviceService.change(deviceId, amountToAdd, unit, level);
    }

    @DeleteMapping("delete-device")
    @ApiOperation("批量删除设备!")
    public Boolean deleteDevices(@RequestBody List<String> deviceIds) {
        return deviceService.removeBatchByIds(deviceIds);
    }

    @PostMapping("verify")
    @ApiOperation(value = "验证设备是否授权(无需操作)", tags = {"Public"}) //方法介绍
    public Device verify(@RequestBody Map<String, Object> data) {
        final String key = "ding-dong:device";
        Assert.isTrue(Boolean.TRUE.equals(redisTemplate.hasKey(key)), "脚本不存在!!");
        Assert.isTrue(data.get("versions").equals(redisTemplate.opsForHash().get(key, "versions")), "脚本版本以过期!!请联系作者!");
        return deviceService.verify((String) data.get("deviceId"), (String) data.get("versions"));
    }
}
