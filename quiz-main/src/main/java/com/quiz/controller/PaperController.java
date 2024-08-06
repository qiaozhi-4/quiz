package com.quiz.controller;


import com.quiz.annotation.PathPermission;
import com.quiz.dto.PaperDto;
import com.quiz.entity.Paper;
import com.quiz.enumerate.PermissionEnum;
import com.quiz.service.IPaperService;
import com.quiz.utils.JWTUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    private final IPaperService paperService;

    @PathPermission(PermissionEnum.USER_CREATE)
    @ApiOperation("保存试卷信息")
    @PostMapping("save-exam")
    public PaperDto saveExam(@RequestBody PaperDto paperDto) {
        return paperService.addPaper(paperDto);
    }

    @PathPermission(PermissionEnum.READ)
    @ApiOperation("通过试卷ID,获取试卷详情")
    @GetMapping("get/{paperId:\\d+}")
    public PaperDto get(@PathVariable Integer paperId) {
        return paperService.getPaper(paperId);
    }

    @PathPermission(PermissionEnum.READ)
    @ApiOperation("获取试卷列表")
    @GetMapping("get-all")
    public List<Paper> getAll(@RequestHeader String token) {
        val userId = Integer.parseInt(JWTUtils.getMemberIdByJwtToken(token));
        return paperService.getPaperListByUserId(userId);
    }


    @PathPermission(PermissionEnum.USER_DELETE)
    @ApiOperation("删除试卷")
    @GetMapping("delete/{paperId:\\d+}")
    public Boolean delete(@PathVariable Integer paperId) {
        return paperService.deletePaperByPaperId(paperId);
    }

}
