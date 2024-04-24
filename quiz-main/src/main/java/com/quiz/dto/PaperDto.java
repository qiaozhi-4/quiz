package com.quiz.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.quiz.entity.Paper;
import com.quiz.entity.Question;
import com.quiz.entity.Tag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * <p>
 * PaperDto
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/23 19:37
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "PaperDto对象", description = "试卷所有信息")
public class PaperDto extends Paper {

    @ApiModelProperty("试卷题目集合")
    @TableField("questions")
    private List<Question> questions;

    @ApiModelProperty("试卷标签集合")
    @TableField("tags")
    private List<Tag> tags;

    /**
     * 转换为 entity
     */
    public static Paper convertFor(PaperDto paperDto) {
        final Paper build = Paper.builder().build();
        BeanUtils.copyProperties(paperDto, build);
        return build;
    }

    /**
     * 转换为 dto
     */
    public static PaperDto convertOf(Paper paper) {
        final PaperDto build = new PaperDto();
        BeanUtils.copyProperties(paper, build);
        return build;
    }
}
