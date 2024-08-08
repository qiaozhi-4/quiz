package com.quiz.mapper;

import com.quiz.dto.PropDTO;
import com.quiz.entity.Prop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 道具表 Mapper 接口
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-08
 */
public interface PropMapper extends BaseMapper<Prop> {
    /**
     * 根据用户id查询道具列表
     * @param userId 用户id
     */
    List<PropDTO> selectPropsByUserId(Integer userId);
}
