package com.luren.wechat.controller;

import com.luren.wechat.common.base.HttpResult;
import com.luren.wechat.service.IAuthService;
import com.luren.wechat.service.dto.AuthWxUserDto;
import com.luren.wechat.service.dto.TokenDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;


/**
 * api登录授权
 *
 * @author LHH
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class AuthController {
    @Resource
    private IAuthService iAuthService;

    @PostMapping(value = "/login")
    public HttpResult login(@RequestBody AuthWxUserDto authWxUserDto) {
        AuthWxUserDto authWxUserDtoResult = iAuthService.login(authWxUserDto);
        HttpResult httpResult = HttpResult.success();
        httpResult.put("userInfo",authWxUserDtoResult.getUserInfo());
        httpResult.put("token",authWxUserDtoResult.getToken());
        return httpResult;
    }


    @PostMapping(value = "/token")
    public HttpResult checkToken(@RequestBody TokenDto tokenDto) {
        if(iAuthService.checkToken(tokenDto)){
            return HttpResult.success();
        }else {
            return HttpResult.error();
        }
    }

    @PostMapping(value = "/update")
    public HttpResult updateUser(@RequestBody AuthWxUserDto authWxUserDto) {
        if(iAuthService.updateUser(authWxUserDto)){
            return HttpResult.success();
        }else {
            return HttpResult.error();
        }
    }
}
