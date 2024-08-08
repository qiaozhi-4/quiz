package com.quiz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.quiz.entity.UserProp;
import com.quiz.mapper.UserPropMapper;
import com.quiz.service.IUserPropService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quiz.utils.Assert;
import lombok.val;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户关联道具表 服务实现类
 * </p>
 *
 * @author XGeorge
 * @since 2024-08-08
 */
@Service
public class UserPropServiceImpl extends ServiceImpl<UserPropMapper, UserProp> implements IUserPropService {

    @Override
    public UserProp getUserPropByUserIdAndPropId(Integer userId, Integer propId) {
        val opt = this.getOneOpt(new LambdaQueryWrapper<UserProp>()
                .eq(UserProp::getUserId, userId)
                .eq(UserProp::getPropId, propId));
        if (opt.isPresent()) {
            return opt.get();
        } else {
            /* 该道具还未存在,初始化这个道具 */
            UserProp userProp = UserProp.builder().userId(userId).propId(propId).number(0).build();
            userProp.insert();
            return userProp;
        }
    }

    @Override
    public UserProp setProp(Integer userId, Integer propId, Integer num) {
        UserProp userProp = this.getUserPropByUserIdAndPropId(userId, propId);
        Assert.isTrue(num >= 0, "道具数量不能为负数");
        userProp.setNumber(num).updateById();
        return userProp;
    }

    @Override
    public UserProp useProp(Integer userId, Integer propId, Integer num) {
        UserProp userProp = this.getUserPropByUserIdAndPropId(userId, propId);
        Assert.isTrue(num > 0, "使用道具数量必须大于1");
        Assert.isTrue(userProp.getNumber() - num >= 0, "道具不足");
        userProp.setNumber(userProp.getNumber() - num).updateById();
        return userProp;
    }

    @Override
    public UserProp gainProp(Integer userId, Integer propId, Integer num) {
        UserProp userProp = this.getUserPropByUserIdAndPropId(userId, propId);
        Assert.isTrue(num > 0, "获取道具数量必须大于1");
        userProp.setNumber(userProp.getNumber() + num).updateById();
        return userProp;
    }
}
