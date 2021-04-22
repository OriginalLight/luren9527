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
@Table(name = "wx_leave")
public class WxLeave implements Serializable {

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
     * 出发时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "from_time", nullable = false)
    private Date fromTime;

    /**
     * 到达时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "to_time", nullable = false)
    private Date toTime;

    /**
     * 状态
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 审核状态
     */
    @Column(name = "check_status")
    private Integer checkStatus;

    /**
     * 审核人
     */
    @Column(name = "check_by")
    private String checkBy;

    /**
     * 审核时间
     */
    @Column(name = "check_time")
    private Date checkTime;

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
