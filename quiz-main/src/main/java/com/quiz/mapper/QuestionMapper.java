package com.quiz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quiz.entity.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 题目表 Mapper 接口
 * </p>
 *
 * @author XGeorge
 * @since 2024-04-23  0224:01:42
 */
public interface QuestionMapper extends BaseMapper<Question> {
    /**
     * 随机返回 num 个题目
     *
     * @param userId 用户id
     * @param num    题目数量
     */
    List<Question> selectRandomQuestions(@Param("userId") Integer userId, @Param("num") Integer num);
}
