package com.quiz.service;

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
     * 保存用户信息
     *
     * @param userId        用户id
     * @param encryptedData 消息密文
     * @param ivStr         加密算法的初始向量
     * @return Result
     */
    Boolean saveUserInfo(Integer userId, String encryptedData, String ivStr);
}
