package com.quiz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 题目试卷
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-22  1124:00:15
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("q_paper")
@ApiModel(value = "Paper对象", description = "题目试卷")
public class Paper extends Model<Paper> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("唯一主键")
    @TableId(value = "paper_id", type = IdType.AUTO)
    private Integer paperId;

    @ApiModelProperty("出题用户id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("封面url")
    @TableField("cover_url")
    private String coverUrl;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("描述")
    @TableField("describe_")
    private String describe;

    @ApiModelProperty("答案下标集合,以\",\"分割")
    @TableField("answers")
    private String answers;


    @Override
    public Serializable pkVal() {
        return this.paperId;
    }

}
