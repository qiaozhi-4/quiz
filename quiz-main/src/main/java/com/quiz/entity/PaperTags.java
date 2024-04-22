package com.quiz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 试卷关联标签
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
@TableName("q_paper_tags")
@ApiModel(value = "PaperTags对象", description = "试卷关联标签")
public class PaperTags extends Model<PaperTags> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("试卷ID。")
    @TableField("paper_id")
    private Integer paperId;

    @ApiModelProperty("标签ID。")
    @TableField("tag_id")
    private Integer tagId;


    @Override
    public Serializable pkVal() {
        return null;
    }

}
