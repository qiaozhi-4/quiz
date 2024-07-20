package com.quiz.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.quiz.enumerate.ResultEnum;
import com.quiz.exception.APIException;

import java.util.Objects;

/**
 * <p>
 * Assert 断言工具类
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/24 16:23
 */
public class Assert {
    public static void isTrue(boolean expression, Integer code, String message) {
        if (!expression)
            throw APIException.builder().code(code).message(message).build();
    }

    public static void isTrue(boolean expression, ResultEnum res) {
        isTrue(expression, res.getCode(), res.getMessage());
    }

    public static void isTrue(boolean expression, String message) {
        isTrue(expression, ResultEnum.FAILED_BACK_END_ERROR.getCode(), message);
    }

    public static void isNotNull(String expression, Integer code, String message) {
        if (StringUtils.isBlank(expression))
            throw APIException.builder().code(code).message(message).build();
    }

    public static void isNotNull(String expression, ResultEnum res) {
        isNotNull(expression, res.getCode(), res.getMessage());
    }

    public static void isNotNull(String expression, String message) {
        isNotNull(expression, ResultEnum.FAILED_BACK_END_ERROR.getCode(), message);
    }

    public static void isNotNull(Object expression,Integer code, String message) {
        if (Objects.isNull(expression))
            throw APIException.builder().code(code).message(message).build();
    }

    public static void isNotNull(Object expression,  ResultEnum res) {
        isNotNull(expression, res.getCode(), res.getMessage());
    }

    public static void isNotNull(Object expression, String message) {
        isNotNull(expression, ResultEnum.FAILED_BACK_END_ERROR.getCode(), message);
    }
}
