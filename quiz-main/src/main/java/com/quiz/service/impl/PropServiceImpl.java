package com.quiz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.dto.PropDTO;
import com.quiz.entity.Prop;
import com.quiz.mapper.PropMapper;
import com.quiz.service.IPropService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 道具表 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-09
 */
@Service
@AllArgsConstructor
public class PropServiceImpl extends ServiceImpl<PropMapper, Prop> implements IPropService {

    @Override
    public List<PropDTO> getPropsByUserId(Integer userId) {
        return this.baseMapper.selectPropsByUserId(userId);
    }
}
