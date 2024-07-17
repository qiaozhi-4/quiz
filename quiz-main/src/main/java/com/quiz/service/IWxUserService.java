package com.quiz.service;

import com.quiz.entity.User;
import com.quiz.utils.Result;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * <p>
 * IWxUserService
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/19 21:55
 */
public interface IWxUserService {
    /**
     * 微信登录
     *
     * @param code 微信临时登录凭证code
     * @return 登录成功返回token信息
     */
    Result<Object> login(String code) throws WxErrorException;

    /**
     * 修改用户信息
     *
     * @param user 用户id
     */
    User updateUserInfo(User user);
}
