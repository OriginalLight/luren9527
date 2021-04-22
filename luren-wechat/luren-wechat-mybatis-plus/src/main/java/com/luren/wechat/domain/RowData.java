package com.luren.wechat.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class RowData implements Serializable {
    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 性别   0 男  1  女  2 人妖
     */
    private Integer gender;

    /**
     * 所在国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 语言
     */
    private String language;

    private static final long serialVersionUID = 1L;
}
