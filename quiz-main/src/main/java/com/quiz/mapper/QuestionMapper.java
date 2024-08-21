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
 * @since 2024-08-20
 */
public interface QuestionMapper extends BaseMapper<Question> {
    /**
     * 随机获取 num 个该用户没有出过的题目
     *
     * @param userId 用户id
     * @param num    题目数量
     */
    List<Question> selectRandomQuestionsByUserIdAndNumber(@Param("userId") Integer userId, @Param("num") Integer num);
}
