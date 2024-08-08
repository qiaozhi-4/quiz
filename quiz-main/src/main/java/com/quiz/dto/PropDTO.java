package com.quiz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * PropDTO
 * </p>
 *
 * @author XGeorge
 * @since 2024/8/8 下午12:58
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "PropDTO对象", description = "道具表")
public class PropDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("道具id。")
    private Integer propId;

    @ApiModelProperty("道具名称")
    private String propName;

    @ApiModelProperty("道具数量")
    private Integer number;
}
