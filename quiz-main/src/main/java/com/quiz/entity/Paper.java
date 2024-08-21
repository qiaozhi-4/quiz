package com.quiz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;
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
 * 题目试卷
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
@TableName("q_paper")
@ApiModel(value = "Paper对象", description = "题目试卷")
public class Paper extends Model<Paper> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键,试卷id")
    @TableId(value = "paper_id", type = IdType.AUTO)
    private Integer paperId;

    @ApiModelProperty("出题用户id")
    @TableField("creator_user_id")
    private Integer creatorUserId;

    @ApiModelProperty("序号")
    @TableField("order_")
    private Integer order;

    @ApiModelProperty("状态")
    @TableField("state")
    private Integer state;

    @ApiModelProperty("封面url")
    @TableField("cover_url")
    private String coverUrl;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("描述")
    @TableField("describe_")
    private String describe;

    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @ApiModelProperty("修改时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;


    @Override
    public Serializable pkVal() {
        return this.paperId;
    }

}
