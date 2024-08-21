package com.quiz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * QuestionDTO
 * </p>
 *
 * @author XGeorge
 * @since 2024/8/20 下午3:53
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "QuestionDTO对象", description = "题目表")
public class QuestionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键,题目id")
    private Integer questionId;
    @ApiModelProperty("题目")
    private String title;
    @ApiModelProperty("选项,以@@分隔")
    private String options;
    @ApiModelProperty("类别id")
    private Integer classId;
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("修改时间")
    private LocalDateTime updatedAt;


    @ApiModelProperty("类别名称")
    private String className;


    @ApiModelProperty("主键")
    private Integer aqId;
    @ApiModelProperty("答卷id")
    private Integer answerId;
    @ApiModelProperty("答题人选择下标")
    private Integer aqSelectIndex;
    @ApiModelProperty("答题人额外描述")
    private String aqExtraDescribe;


    @ApiModelProperty("试卷id")
    private Integer pqId;
    @ApiModelProperty("试卷ID")
    private Integer paperId;
    @ApiModelProperty("出题人选择下标")
    private Integer pqSelectIndex;
    @ApiModelProperty("出题人额外描述")
    private String pqExtraDescribe;
}
