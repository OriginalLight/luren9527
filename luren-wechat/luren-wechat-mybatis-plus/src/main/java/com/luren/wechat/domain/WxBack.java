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
@TableName(value = "wx_back")
public class WxBack implements Serializable {
    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 微信用户编号
     */
    @TableField(value = "open_id")
    private String openId;

    /**
     * 起始地
     */
    @TableField(value = "from_place")
    private String fromPlace;

    /**
     * 出发时间
     */
    @TableField(value = "from_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date fromTime;

    /**
     * 状态
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 审核状态
     */
    @TableField(value = "check_status")
    private Integer checkStatus;

    /**
     * 审核人
     */
    @TableField(value = "check_by")
    private String checkBy;

    /**
     * 审核时间
     */
    @TableField(value = "check_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date checkTime;

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

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    private static final long serialVersionUID = 1L;
}