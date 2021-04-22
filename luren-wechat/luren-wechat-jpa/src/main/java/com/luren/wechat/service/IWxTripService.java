package com.luren.wechat.service;

import com.luren.wechat.entity.WxTrip;
import com.luren.wechat.service.dto.WxTripDto;
import com.luren.wechat.service.dto.WxUserDto;

import java.util.List;

/**
 * @author LHH
 */
public interface IWxTripService {
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
