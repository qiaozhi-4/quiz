package com.quiz.utils;

import com.quiz.enumerate.ResultEnum;
import com.quiz.exception.APIException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * Result 封装统一响应实体类
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/6 12:04
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "统一的响应体")
public class Result<T> {
    @ApiModelProperty("状态码")
    private Integer code;
    @ApiModelProperty("描述")
    private String message;
    @ApiModelProperty("数据")
    private T data;

    private Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }


    public static <T> Result<T> instance(Integer code, String message, T data) {
        return new Result<>(code, message, data);
    }

    public static <T> Result<T> instance(ResultEnum resultEnum, T data) {
        return instance(resultEnum.getCode(), resultEnum.getMessage(), data);
    }

    public static <T> Result<T> instance(ResultEnum resultEnum) {
        return instance(resultEnum, null);
    }

    public static <T> Result<T> success(String message, T data) {
        return instance(ResultEnum.SUCCESS.getCode(), message, data);
    }

    public static <T> Result<T> success(String message) {
        return success(message, null);
    }

    public static <T> Result<T> success(T data) {
        return instance(ResultEnum.SUCCESS, data);
    }

    public static <T> Result<T> success() {
        return instance(ResultEnum.SUCCESS);
    }


    public static <T> Result<T> failed(String message, T data) {
        return instance(ResultEnum.FAILED_BACK_END_ERROR.getCode(), message, data);
    }

    public static <T> Result<T> failed(String message) {
        return failed(message, null);
    }

    public static <T> Result<T> failed(ResultEnum resultEnum, T data) {
        return instance(resultEnum, data);
    }

    public static <T> Result<T> failed(ResultEnum resultEnum) {
        return failed(resultEnum, null);
    }

    public static <T> Result<T> failed(APIException e, T data) {
        return instance(e.getCode(), e.getMessage(), data);
    }

    public static <T> Result<T> failed(APIException e) {
        return failed(e, null);
    }


}
