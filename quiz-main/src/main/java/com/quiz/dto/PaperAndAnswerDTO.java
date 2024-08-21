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
 * PaperAndAnswerDTO
 * </p>
 *
 * @author XGeorge
 * @since 2024/8/21 下午4:48
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "PaperAndAnswerDTO对象", description = "包含试卷和答卷信息")
public class PaperAndAnswerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("试卷id")
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
    @ApiModelProperty("修改时间")
    private LocalDateTime updatedAt;


    @ApiModelProperty("答卷id ")
    private Integer answerId;
    @ApiModelProperty("答题用户id")
    private Integer responderUserId;
    @ApiModelProperty("得分")
    private Integer score;


    @ApiModelProperty("出题用户昵称")
    private String creatorUserNickname;
    @ApiModelProperty("出题用户头像")
    private String creatorUserAvatarUrl;
    @ApiModelProperty("出答题用户昵称")
    private String responderUserNickname;
    @ApiModelProperty("答题用户头像")
    private String responderUserAvatarUrl;
    @ApiModelProperty("试卷题目集合")
    private List<QuestionDTO> questions;
    @ApiModelProperty("用户答题总数,被多少人做过")
    private Long answerTotal;
}
