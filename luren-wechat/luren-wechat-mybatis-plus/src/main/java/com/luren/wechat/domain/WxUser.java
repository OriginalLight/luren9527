package com.luren.wechat.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LHH
 */
@Data
@TableName(value = "wx_user")
public class WxUser implements Serializable {
    /**
     * 小程序用户openid
     */
    @TableId(value = "open_id", type = IdType.INPUT)
    private String openId;

    /**
     * 部门ID
     */
    @TableField(value = "dept_id")
    private Long deptId;

    @TableField(value = "union_id")
    private String unionId;

    /**
     * 用户头像
     */
    @TableField(value = "avatar_url")
    private String avatarUrl;

    /**
     * 用户昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 性别   0 男  1  女  2 人妖
     */
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 所在国家
     */
    @TableField(value = "country")
    private String country;

    /**
     * 省份
     */
    @TableField(value = "province")
    private String province;

    /**
     * 城市
     */
    @TableField(value = "city")
    private String city;

    /**
     * 姓名
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 学号
     */
    @TableField(value = "`number`")
    private Integer number;

    /**
     * 手机号码
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 紧急联系人
     */
    @TableField(value = "emergency_contact")
    private String emergencyContact;

    /**
     * 紧急联系人手机号码
     */
    @TableField(value = "emergency_contact_mobile")
    private String emergencyContactMobile;

    /**
     * 现居住地
     */
    @TableField(value = "`location`")
    private String location;

    /**
     * 状态  0：禁用   1：正常
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}