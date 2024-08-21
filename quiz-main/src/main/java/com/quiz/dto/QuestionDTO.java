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


    @ApiModelProperty("试卷/答题和题目关联表主键")
    private Integer id;
    @ApiModelProperty("类别名称")
    private String className;
    @ApiModelProperty("答案的下标")
    private Integer answerIndex;
    @ApiModelProperty("选择的下标")
    private Integer selectIndex;
    @ApiModelProperty("答案的描述")
    private String answerDescribe;
    @ApiModelProperty("选择的描述")
    private String selectDescribe;
}
