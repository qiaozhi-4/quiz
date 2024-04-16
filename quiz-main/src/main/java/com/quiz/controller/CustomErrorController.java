package com.quiz.controller;

import com.quiz.enumerate.ResultEnum;
import com.quiz.utils.Result;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * CustomErrorController 自定义处理所有未捕获异常
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/16 13:51
 */
@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {
    @ResponseBody
    @RequestMapping
    public Result<Object> error() {
        return Result.failed(ResultEnum.FAILED_BACK_END_ERROR_UN);
    }
}
