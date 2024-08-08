package com.quiz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.dto.PropDTO;
import com.quiz.entity.Prop;
import com.quiz.entity.UserProp;
import com.quiz.mapper.PropMapper;
import com.quiz.service.IPropService;
import com.quiz.service.IUserPropService;
import com.quiz.utils.Assert;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 道具表 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-08
 */
@Service
@AllArgsConstructor
public class PropServiceImpl extends ServiceImpl<PropMapper, Prop> implements IPropService {
    private final IUserPropService userPropService;

    @Override
    public List<PropDTO> getPropsByUserId(Integer userId) {
        return this.baseMapper.selectPropsByUserId(userId);
    }

    @Override
    public Boolean useProp(Integer userId, Integer propId, Integer num) {
        val present = userPropService.getOneOpt(new LambdaQueryWrapper<UserProp>()
                .eq(UserProp::getUserId, userId)
                .eq(UserProp::getPropId, propId));
        Assert.isTrue(present.isPresent() && present.get().getNumber() - num >= 0, "道具不足");
        present.get().setNumber(present.get().getNumber() - num)
                .updateById();
        return true;
    }
}
