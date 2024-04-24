package com.quiz.utils;

import com.quiz.exception.APIException;

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
            throw APIException.builder().message(message).build();
    }
}
