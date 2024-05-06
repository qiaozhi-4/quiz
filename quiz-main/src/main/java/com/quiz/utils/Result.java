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

    public static <T> Result<T> success() {
        return new Result<>(ResultEnum.SUCCESS);
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultEnum.SUCCESS).setData(data);
    }

    public static <T> Result<T> failed() {
        return new Result<>(ResultEnum.FAILED);
    }

    public static <T> Result<T> failed(String message) {
        return new Result<T>(ResultEnum.FAILED).setMessage(message);
    }

    public static <T> Result<T> failed(ResultEnum resultEnum) {
        return new Result<>(resultEnum);
    }

    public static <T> Result<T> failed(APIException e) {
        return new Result<T>().setCode(e.getCode()).setMessage(e.getMessage());
    }

    public static <T> Result<T> failed(ResultEnum resultEnum, T data) {
        return new Result<T>(resultEnum).setData(data);
    }

    public static <T> Result<T> instance(ResultEnum resultEnum) {
        return new Result<>(resultEnum);
    }

    public static <T> Result<T> instance(ResultEnum resultEnum, T data) {
        return new Result<T>(resultEnum).setData(data);
    }
}
