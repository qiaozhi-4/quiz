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
    public static void isTrue(boolean expression, String message) {
        if (!expression)
            throw APIException.builder().message(message).code(ResultEnum.FAILED.getCode()).build();
    }

    public static void isNotNull(String expression, String message) {
        if (StringUtils.isBlank(expression))
            throw APIException.builder().message(message).code(ResultEnum.FAILED.getCode()).build();
    }

    public static void isNotNull(Object expression, String message) {
        if (Objects.isNull(expression))
            throw APIException.builder().message(message).code(ResultEnum.FAILED.getCode()).build();
    }
}
