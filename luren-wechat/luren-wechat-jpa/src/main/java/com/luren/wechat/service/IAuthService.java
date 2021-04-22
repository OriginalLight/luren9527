package com.luren.wechat.service;

import com.luren.wechat.service.dto.AuthWxUserDto;
import com.luren.wechat.service.dto.TokenDto;

/**
 * 登录授权服务接口
 *
 * @author LHH
 */
public interface IAuthService {

    /**
     * 登录授权
     *
     * @param authWxUserDto 认证用户请求信息
     * @return 认证用户返回信息
     */
    AuthWxUserDto login(AuthWxUserDto authWxUserDto);

    Boolean checkToken(TokenDto token);

    Boolean updateUser(AuthWxUserDto authWxUserDto);
}
