package com.luren.wechat.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.luren.wechat.common.constant.Constant;
import com.luren.wechat.entity.WxUser;
import com.luren.wechat.service.IAuthService;
import com.luren.wechat.service.IWxUserService;
import com.luren.wechat.service.dto.AuthWxUserDto;
import com.luren.wechat.service.dto.TokenDto;
import com.luren.wechat.utils.JwtTokenUtils;
import com.luren.wechat.service.IWxMiniApi;
import com.luren.wechat.utils.WeChatUtil;
import com.luren.wechat.common.config.JwtSecurityProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 授权登录接口实现类
 *
 * @author LHH
 */

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AuthServiceImpl implements IAuthService {

    @Value("${wxMini.appId}")
    private String appId;
    @Value("${wxMini.secret}")
    private String secret;

    @Resource
    private JwtTokenUtils jwtTokenUtils;
    @Resource
    private IWxMiniApi iWxMiniApi;
    @Resource
    private IWxUserService iWxUserService;
    @Resource
    private JwtSecurityProperties properties;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public AuthWxUserDto login(AuthWxUserDto authWxUserDto) {
        if (!StringUtils.isEmpty(authWxUserDto.getCode())) {
            JSONObject jsonObject = iWxMiniApi.authCode2Session(appId, secret, authWxUserDto.getCode());
            if (jsonObject == null) {
                throw new RuntimeException("调用微信端授权认证接口错误");
            }
            String openId = jsonObject.getString(Constant.OPEN_ID);
            String sessionKey = jsonObject.getString(Constant.SESSION_KEY);
            String unionId = jsonObject.getString(Constant.UNION_ID);
            authWxUserDto.setOpenId(openId);
            WxUser resultUser = iWxUserService.findByOpenId(openId);
            if (StringUtils.isEmpty(resultUser)) {
                String userInfo = WeChatUtil.decryptData(authWxUserDto.getEncryptedData(), sessionKey, authWxUserDto.getIv());
                if (StringUtils.isEmpty(userInfo)) {
                    throw new RuntimeException("解密用户信息错误");
                }
                WxUser user = JSONObject.parseObject(userInfo, WxUser.class);
                if (user == null) {
                    throw new RuntimeException("填充用户对象错误");
                }
                user.setOpenId(openId);
                user.setUnionId(unionId);
                user.setStatus(1);
                user.setCreateBy("system");
                user.setUpdateBy("system");
                Date date = DateUtil.date(System.currentTimeMillis());
                user.setCreateTime(date);
                user.setUpdateTime(date);
                iWxUserService.create(user);
                authWxUserDto.setUserInfo(user);
            } else {
                iWxUserService.update(resultUser);
                authWxUserDto.setUserInfo(resultUser);
            }
        }
        Map<String, Object> claims = new HashMap<>(16);
        claims.put("roles", "user");
        String token = jwtTokenUtils.createToken(authWxUserDto.getOpenId(), claims);
        if (StringUtils.isEmpty(token)) {
            throw new RuntimeException("生成token错误");
        }
        authWxUserDto.setToken(properties.getTokenStartWith() + token);
        return authWxUserDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean checkToken(TokenDto tokenDto) {
        Date date = jwtTokenUtils.getExpirationDateFromToken(tokenDto.getToken().replaceFirst("Bearer ",""));
        Date currentTime = new Date(System.currentTimeMillis());
        if(date == null){
            return false;
        }else {
            return currentTime.compareTo(date) <= 0;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateUser(AuthWxUserDto authWxUserDto) {
        WxUser user = authWxUserDto.getUserInfo();
        user.setUpdateBy(user.getName());
        Date date = DateUtil.date(System.currentTimeMillis());
        user.setUpdateTime(date);
        try {
            iWxUserService.update(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
