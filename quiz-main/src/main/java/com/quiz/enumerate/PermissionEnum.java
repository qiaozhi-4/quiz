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
    CREATE("CREATE", "添加"),
    READ("READ", "读取"),
    UPDATE("UPDATE", "更新"),
    DELETE("DELETE", "删除"),
    DD_CHANGE("DD_CHANGE", "勇者的修改权限"),
    USER_CREATE("USER_CREATE", "用户添加自己相关"),
    USER_UPDATE("USER_UPDATE", "用户更新自己相关"),
    USER_DELETE("USER_DELETE", "用户删除自己相关"),
    UPDATE_OWN("UPDATE_OWN", "修改用户自己");

    private final String name;
    private final String describe;

    PermissionEnum(String name, String describe) {
        this.name = name;
        this.describe = describe;
    }
}
