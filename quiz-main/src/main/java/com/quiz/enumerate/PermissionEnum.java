package com.quiz.enumerate;

import lombok.Getter;

/**
 * <p>
 * PermissionEnum 权限枚举
 * </p>
 *
 * @author XGeorge
 * @since 2024/7/16 下午6:12
 */
@Getter
public enum PermissionEnum {
    CREATE("CREATE"),
    READ("READ"),
    UPDATE("UPDATE"),
    DELETE("DELETE"),
    DD_CREATE("DD_CREATE");
    private String name;


    PermissionEnum(String name) {
        this.name = name;
    }

    public PermissionEnum setName(String name) {
        this.name = name;
        return this;
    }
}
