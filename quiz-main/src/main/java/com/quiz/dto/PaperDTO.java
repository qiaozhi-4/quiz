package com.quiz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * PaperDTO
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/23 19:37
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "PaperDto对象", description = "试卷所有信息")
public class PaperDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("唯一主键")
    private Integer paperId;

    @ApiModelProperty("出题用户id")
    private Integer creatorUserId;

    @ApiModelProperty("序号")
    private Integer order;

    @ApiModelProperty("状态")
    private Integer state;

    @ApiModelProperty("封面url")
    private String coverUrl;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("描述")
    private String describe;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty("试卷题目集合")
    private List<QuestionDTO> questions;

    @ApiModelProperty("用户答题总数,被多少人做过")
    private Long answerTotal;

    @ApiModelProperty("答卷id(自己回答这个试卷的信息)")
    private Integer answerId;

    @ApiModelProperty("得分(自己回答这个试卷的信息)")
    private Integer score;

}
