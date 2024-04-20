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
     * <p>
     * 微信登录
     * </p>
     *
     * @param code 微信临时登录凭证code
     * @return 登录成功返回token信息
     */
    Result<Object> login(String code) throws WxErrorException;
}
