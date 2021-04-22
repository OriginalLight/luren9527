package com.luren.wechat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luren.wechat.domain.WxTrip;
import com.luren.wechat.service.dto.WxTripDto;
import com.luren.wechat.service.dto.WxUserDto;

import java.util.List;

/**
 * @author LHH
 */
public interface IWxTripService extends IService<WxTrip>{
    /**
     *
     * @return ture/false
     */
    Boolean add(WxTripDto wxTripDto);

    /**
     *
     * @param wxUserDto
     */
    List<WxTrip> findAllByOpenId(WxUserDto wxUserDto);

}
