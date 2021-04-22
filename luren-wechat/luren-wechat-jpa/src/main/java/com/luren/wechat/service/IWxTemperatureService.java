package com.luren.wechat.service;

import com.luren.wechat.service.dto.WxTemperatureDto;
import com.luren.wechat.service.dto.WxUserDto;

/**
 * @author LHH
 */
public interface IWxTemperatureService {

    /**
     * @return ture/false
     */
    Boolean add(WxTemperatureDto wxTemperatureDto);

    /**
     * @param wxUserDto
     */
    Object findAllByOpenId(WxUserDto wxUserDto);

    /**
     * 获取健康状态
     * @param days
     * @return true/false
     */
    Integer healthStatus(Integer days,String openId);

    /**
     * @param wxUserDto
     * @return
     */
    Boolean getToDay(WxUserDto wxUserDto);

}
