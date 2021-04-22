package com.luren.wechat.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author LHH
 */
@Accessors(chain = true)
@Data
@Entity
@Table(name = "wx_trip")
public class WxTrip implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 微信用户编号
     */
    @Column(name = "open_id", nullable = false)
    private String openId;

    /**
     * 起始地
     */
    @Column(name = "from_place", nullable = false)
    private String fromPlace;

    /**
     * 目的地
     */
    @Column(name = "to_place", nullable = false)
    private String toPlace;

    /**
     * 出发时间
     */
    @Column(name = "from_time", nullable = false)
    private Date fromTime;

    /**
     * 到达时间
     */
    @Column(name = "to_time", nullable = false)
    private Date toTime;

    /**
     * 状态
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
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

}
