package com.quiz.controller;


import com.quiz.entity.UseInfo;
import com.quiz.service.IUseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 脚本使用信息 前端控制器
 * </p>
 *
 * @author XGeorge
 * @since 2024-07-14
 */
@RestController
@RequestMapping("/use-info")
@Api(tags = "脚本使用信息 前端控制器")
@RequiredArgsConstructor
public class UseInfoController {

    private final IUseInfoService useInfoService;

    @PostMapping("send-log")
    @ApiOperation(value = "保存用户使用功能日志", tags = {"Public"})
    public String sendLog(@RequestBody UseInfo useInfo) {
        useInfoService.save(useInfo);
        return "保存日志成功!";
    }
}
