package com.luren.wechat.service.dto;

import com.luren.wechat.domain.WxBack;
import com.luren.wechat.domain.WxUser;
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
