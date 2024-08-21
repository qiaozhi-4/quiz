package com.quiz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 试卷关联题目
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-21
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("q_paper_questions")
@ApiModel(value = "PaperQuestions对象", description = "试卷关联题目")
public class PaperQuestions extends Model<PaperQuestions> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("试卷ID")
    @TableField("paper_id")
    private Integer paperId;

    @ApiModelProperty("答卷id")
    @TableField("answers_id")
    private Integer answersId;

    @ApiModelProperty("题目ID")
    @TableField("question_id")
    private Integer questionId;

    @ApiModelProperty("出题人/答题人选择下标")
    @TableField("select_index")
    private Integer selectIndex;

    @ApiModelProperty("出题人/答题人额外描述")
    @TableField("extra_describe")
    private String extraDescribe;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
