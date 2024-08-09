package com.quiz.dto;

import com.quiz.entity.Question;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * AnswersDTO
 * </p>
 *
 * @author XGeorge
 * @since 2024/8/9 下午12:33
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "AnswersDTO对象", description = "题目答卷")
public class AnswersDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("唯一主键")
    private Integer answerId;

    @ApiModelProperty("关联试卷id")
    private Integer paperId;

    @ApiModelProperty("答题用户id")
    private Integer responderUserId;

    @ApiModelProperty("选择下标集合,以@@分隔")
    private String selects;

    @ApiModelProperty("得分")
    private Integer score;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty("试卷题目集合")
    private List<Question> questions;

    @ApiModelProperty("出题用户id")
    private Integer creatorUserId;

    @ApiModelProperty("出题用户昵称")
    private String creatorUserNickname;

    @ApiModelProperty("出题用户头像")
    private String creatorUserAvatarUrl;
}
