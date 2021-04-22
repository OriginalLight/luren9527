package com.luren.wechat.service.dto;

import com.luren.wechat.entity.WxTemperature;
import com.luren.wechat.entity.WxUser;
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
