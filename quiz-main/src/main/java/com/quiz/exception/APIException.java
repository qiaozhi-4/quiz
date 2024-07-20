package com.quiz.exception;

import com.quiz.enumerate.ResultEnum;
import lombok.*;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APIException extends RuntimeException {
    private int code;
    private String message;

    public APIException(String message) {
        this.code = ResultEnum.FAILED_BACK_END_ERROR.getCode();
        this.message = message;
    }

    public APIException(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }
}
