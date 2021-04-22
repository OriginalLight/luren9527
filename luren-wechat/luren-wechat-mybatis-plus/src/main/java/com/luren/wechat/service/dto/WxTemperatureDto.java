package com.luren.wechat.service.dto;

import com.luren.wechat.domain.WxTemperature;
import com.luren.wechat.domain.WxUser;
import lombok.Getter;
import lombok.Setter;

/**
 * @author LHH
 */
@Getter
@Setter
public class WxTemperatureDto {

    private WxTemperature wxTemperature;

    private WxUser wxUser;

}
