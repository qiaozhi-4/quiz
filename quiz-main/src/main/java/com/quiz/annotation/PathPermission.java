package com.quiz.annotation;

import com.quiz.enumerate.PermissionEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * PathPermission 注解方法上表示需要什么权限
 * </p>
 *
 * @author XGeorge
 * @since 2024/7/16 下午10:38
 */
@Retention(RetentionPolicy.RUNTIME) // 注解在运行时可用
@Target({ElementType.METHOD, ElementType.TYPE}) // 注解可用于方法和类
public @interface PathPermission {
    /**
     * 路径所需权限
     */
    PermissionEnum value();
}
