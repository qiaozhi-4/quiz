package com.quiz.mapper;

import com.quiz.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 题目表 Mapper 接口
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-21  1024:23:35
 */
public interface QuestionMapper extends BaseMapper<Question> {
    /**
     * 随机返回10个题目
     */
    List<Question> selectRandomQuestions();

}
