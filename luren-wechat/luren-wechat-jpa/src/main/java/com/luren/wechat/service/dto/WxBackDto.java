package com.luren.wechat.service.dto;

import com.luren.wechat.entity.WxBack;
import com.luren.wechat.entity.WxUser;
import lombok.Getter;
import lombok.Setter;

/**
 * @author LHH
 */
@Getter
@Setter
public class WxBackDto {

    private WxBack wxBack;

    private WxUser wxUser;
}
