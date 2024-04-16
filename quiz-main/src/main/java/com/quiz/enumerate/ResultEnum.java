package com.quiz.enumerate;

/**
 * <p>
 * ResultEnum 响应枚举
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/6 12:04
 */
public enum ResultEnum {
    /* 成功 */
    SUCCESS(200, "ok"),

    /* 失败 */
    FAILED(1000, "error"),

    /* 参数错误：1001～1999 */
    FAILED_PARAM_NOT_VALID(1001, "参数无效"),
    FAILED_PARAM_IS_BLANK(1002, "参数为空"),
    FAILED_PARAM_TYPE_ERROR(1003, "参数类型错误"),
    FAILED_PARAM_NOT_COMPLETE(1004, "参数缺失"),

    /* 用户错误: 2001-2999 */
    FAILED_USER_NOT_LOGIN(2001, "用户未登录"),
    FAILED_USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    FAILED_USER_CREDENTIALS_ERROR(2003, "用户名或密码错误"),
    FAILED_USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    FAILED_USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    FAILED_USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    FAILED_USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    FAILED_USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    FAILED_USER_ACCOUNT_USE_BY_OTHERS(2009, "账号在别处登录下线"),

    /*权限校验*/
    FAILED_NO_PERMISSION(3001, "用户权限不足"),

    /*未知异常*/
    FAILED_BACK_END_ERROR(5001, "后端错误"),
    FAILED_BACK_END_ERROR_UN(5002, "后端错误(未捕获)");

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public ResultEnum setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultEnum setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 根据code获取message
     *
     * @param code 状态码
     * @return msg
     */
    public static String getMsgByCode(Integer code) {
        for (ResultEnum ele : values()) {
            if (ele.getCode() == code) {
                return ele.getMessage();
            }
        }
        return null;
    }
}
