package com.quiz.service;

import cn.binarywang.wx.miniapp.bean.security.WxMaMsgSecCheckCheckRequest;
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
    Result<Object> login(String miniappId, String code) throws WxErrorException;

    /**
     * 修改用户信息
     *
     * @param user 用户id
     */
    User updateUserInfo(User user);

    /**
     * 验证用户输入文本信息
     *
     * @param userId     用户id
     * @param miniappId  小程序id
     * @param msgRequest 请求体
     */
    Boolean verifyText(Integer userId, String miniappId, WxMaMsgSecCheckCheckRequest msgRequest) throws WxErrorException;
}
