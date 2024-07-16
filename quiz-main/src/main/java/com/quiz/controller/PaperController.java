package com.quiz.controller;


import com.quiz.annotation.PathPermission;
import com.quiz.dto.PaperDto;
import com.quiz.enumerate.PermissionEnum;
import com.quiz.mapper.PaperMapper;
import com.quiz.service.IPaperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 题目试卷 前端控制器
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-23  1124:39:06
 */
@RestController
@RequestMapping("/paper")
@Api(tags = "题目试卷 前端控制器")
@RequiredArgsConstructor
public class PaperController {

    private final PaperMapper paperMapper;
    private final IPaperService paperService;

    @PathPermission(PermissionEnum.READ)
    @ApiOperation("通过试卷ID,获取试卷详情")
    @GetMapping("get/{paperId:\\d+}")
    public PaperDto get(@PathVariable Integer paperId) {
        return paperMapper.selectPaperById(paperId);
    }

    @PathPermission(PermissionEnum.CREATE)
    @ApiOperation("保存试卷信息")
    @PostMapping("add")
    public Boolean add(@RequestBody PaperDto paperDto) {
        return paperService.addPaperInfo(paperDto);
    }
}
