package com.quiz.exception;

import com.quiz.enumerate.ResultEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * APIException
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/6 20:17
 */
@Getter
@Setter
public class APIException extends RuntimeException {
    private int code;
    private String message;

    public APIException() {
        this.code = ResultEnum.FAILED.getCode();
        this.message = ResultEnum.FAILED.getMessage();
    }
    public APIException(String message) {
        this.code = ResultEnum.FAILED.getCode();
        this.message = message;
    }

    public APIException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public APIException(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }
}
