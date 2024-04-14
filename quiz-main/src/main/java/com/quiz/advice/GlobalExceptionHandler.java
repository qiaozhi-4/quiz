package com.quiz.advice;

import com.quiz.exception.APIException;
import com.quiz.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * GlobalExceptionHandler 全局异常处理
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/6 10:52
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class, Exception.class})
    @ResponseBody
    public Result<String> handleException(Exception e, HttpServletResponse response) {
        return Result.failed(e.getMessage());
    }

    @ExceptionHandler({APIException.class})
    @ResponseBody
    public Result<String> handleAPIException(APIException e, HttpServletResponse response) {
        return Result.failed(e);
    }

}
