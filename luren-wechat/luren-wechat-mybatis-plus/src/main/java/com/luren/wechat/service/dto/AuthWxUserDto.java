package com.luren.wechat.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.luren.wechat.domain.WxUser;
import com.luren.wechat.domain.RowData;
import lombok.Getter;
import lombok.Setter;

/**
 * 认证用户
 *
 * @author LHH
 */
@Getter
@Setter
public class AuthWxUserDto {

    private String code;

    private String openId;

    private String rawData;

    private String signature;

    private String encryptedData;

    private String iv;

    /**
     * 会话密钥
     */
    @JsonIgnore
    private String sessionKey;

    /**
     * 用户在开放平台的唯一标识符
     */
    @JsonIgnore
    private String unionId;

    //以上为微信类传输字段
    //**********************************

    private String token;

    private WxUser userInfo;

    @Override
    public String toString() {
        return "AuthUser{" +
                ", code='" + code + '\'' +
                ", openId='" + openId + '\'' +
                ", token='" + token + '\'' +
                ", userInfo=" + userInfo +
                '}';
    }
}
