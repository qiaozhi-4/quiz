package com.quiz.controller;


import com.quiz.annotation.PathPermission;
import com.quiz.dto.DeviceDTO;
import com.quiz.entity.Device;
import com.quiz.enumerate.PermissionEnum;
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

    @PathPermission(PermissionEnum.READ)
    @GetMapping("list/{current:\\d+}/{size:\\d+}")
    @ApiOperation("分页获取数据集合、总条数")
    @ApiImplicitParams({ //参数说明
            @ApiImplicitParam(name = "current", value = "第几页", required = true, paramType = "path", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "size", value = "每页多少条", required = true, paramType = "path", dataTypeClass = Integer.class)
    })
    public Map<String, Object> getDeviceList(@PathVariable("current") Integer current, @PathVariable("size") Integer size) {
        return deviceService.paging(current, size);
    }

    @PathPermission(PermissionEnum.DD_CHANGE)
    @PutMapping("update-device")
//    @PutMapping("update")
    @ApiOperation("更新设备信息,是否被封和过期时间")
    public Device updateDevice(@RequestParam String nickname, @RequestBody DeviceDTO deviceDto) {
        Assert.isNotNull(nickname, "修改人昵称不能为空!!");
        Device device = deviceService.change(deviceDto.getDeviceId(), deviceDto.getAmountToAdd(), deviceDto.getAmountUnit(), deviceDto.getLv());
        /*发送邮件*/
        emailUtil.commonEmail(ToEmail.builder().tos(new String[]{"468644641@qq.com"})
                .subject("设备修改")
                .content("修改人:" + nickname + "\n本次修改的设备信息:" + deviceDto)
                .nickname("乔治")
                .build());
        return device;
    }

    @PathPermission(PermissionEnum.DELETE)
    @DeleteMapping("delete-device")
//    @DeleteMapping("delete")
    @ApiOperation("批量删除设备!")
    public Boolean deleteDevices(@RequestBody List<String> deviceIds) {
        return deviceService.removeBatchByIds(deviceIds);
    }

    @PostMapping("verify")
    @ApiOperation(value = "验证设备是否授权(无需操作)") //方法介绍
    public Device verifyDevice(@RequestBody Map<String, Object> data) {
        final String key = "ding-dong:device";
        Assert.isTrue(Boolean.TRUE.equals(redisTemplate.hasKey(key)), "脚本不存在!!");
        Assert.isTrue(data.get("versions").equals(redisTemplate.opsForHash().get(key, "versions")), "脚本版本以过期!!请联系作者!");
        return deviceService.verify((String) data.get("deviceId"), (String) data.get("versions"));
    }
}
