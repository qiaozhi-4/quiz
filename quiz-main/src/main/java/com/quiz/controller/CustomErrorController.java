package com.quiz.controller;

import com.quiz.enumerate.ResultEnum;
import com.quiz.utils.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

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
@Log4j2
public class CustomErrorController implements ErrorController {
    @ResponseBody
    @RequestMapping
    public Result<Object> error(HttpServletRequest request) {
        // 获取错误状态码
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            // 获取具体的错误异常
            Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
            String exceptionMessage = throwable != null ? throwable.getMessage() : "N/A";

            // 日志记录或其他处理
            log.error("Error " + statusCode + " Message: " + exceptionMessage);
            return Result.builder().code(statusCode).message(exceptionMessage).build();
        }
        return Result.failed(ResultEnum.FAILED_BACK_END_ERROR_UN);
    }
}
