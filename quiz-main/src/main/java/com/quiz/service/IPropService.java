package com.quiz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quiz.dto.PropDTO;
import com.quiz.entity.Prop;

import java.util.List;

/**
 * <p>
 * 道具表 服务类
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-08
 */
public interface IPropService extends IService<Prop> {

    /**
     * 获取用户道具
     * @param userId 用户id
     */
    List<PropDTO> getPropsByUserId(Integer userId);
}
