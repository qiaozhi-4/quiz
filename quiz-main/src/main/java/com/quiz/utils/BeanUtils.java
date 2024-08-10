package com.quiz.utils;

import com.quiz.exception.APIException;
import lombok.val;

import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 * BeanUtils
 * </p>
 *
 * @author XGeorge
 * @since 2024/8/10 下午4:16
 */
public class BeanUtils {
    /**
     * 复制属性
     *
     * @param source      源对象
     * @param targetClass 目标对象类
     */
    public static <T> T copyProperties(Object source, Class<T> targetClass) {
        try {
            val object = targetClass.getDeclaredConstructor().newInstance();
            org.springframework.beans.BeanUtils.copyProperties(source, object);
            return object;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new APIException("反射创建对象失败");
        }
    }
}
