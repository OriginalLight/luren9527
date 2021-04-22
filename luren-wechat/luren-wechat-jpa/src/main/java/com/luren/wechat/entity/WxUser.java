package com.luren.wechat.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author LHH
 */
@Accessors(chain = true)
@Data
@Entity
@Table(name = "wx_user")
public class WxUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 小程序用户openid
     */
    @Id
    @Column(name = "open_id", nullable = false)
    private String openId;

    /**
     * 部门ID
     */
    @Column(name = "dept_id")
    private Long deptId;

    @Column(name = "union_id")
    private String unionId;

    /**
     * 用户头像
     */
    @Column(name = "avatar_url")
    private String avatarUrl;

    /**
     * 用户昵称
     */
    @Column(name = "nick_name", nullable = false)
    private String nickName;

    /**
     * 性别   0 男  1  女  2 人妖
     */
    @Column(name = "gender")
    private Integer gender;

    /**
     * 所在国家
     */
    @Column(name = "country")
    private String country;

    /**
     * 省份
     */
    @Column(name = "province")
    private String province;

    /**
     * 城市
     */
    @Column(name = "city")
    private String city;

    /**
     * 省份
     */
    @Column(name = "name")
    private String name;

    /**
     * 城市
     */
    @Column(name = "number")
    private Integer number;
    /**
     * 手机号码
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 紧急联系人
     */
    @Column(name = "emergency_contact")
    private String emergencyContact;

    /**
     * 紧急联系人手机号码
     */
    @Column(name = "emergency_contact_mobile")
    private String emergencyContactMobile;

    /**
     * 现居住地
     */
    @Column(name = "location")
    private String location;

    /**
     * 状态  0：禁用   1：正常
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_time")
    private Date updateTime;

}
