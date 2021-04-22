package com.luren.wechat.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luren.wechat.domain.WxTemperature;
import com.luren.wechat.service.dto.WxTemperatureDto;
import com.luren.wechat.service.dto.WxUserDto;

import java.util.Date;
import java.util.List;

/**
 * @author LHH
 */
public interface IWxTemperatureService extends IService<WxTemperature>{
    /**
     * @return ture/false
     */
    Boolean add(WxTemperatureDto wxTemperatureDto);

    /**
     * @param wxUserDto
     */
    List<WxTemperature> findAllByOpenId(WxUserDto wxUserDto);

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
