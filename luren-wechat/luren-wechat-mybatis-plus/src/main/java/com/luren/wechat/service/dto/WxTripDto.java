package com.luren.wechat.service.dto;

import com.luren.wechat.domain.WxTrip;
import com.luren.wechat.domain.WxUser;
import lombok.Getter;
import lombok.Setter;

/**
 * @author LHH
 */
@Getter
@Setter
public class WxTripDto {

    private WxTrip wxTrip;

    private WxUser wxUser;
}
