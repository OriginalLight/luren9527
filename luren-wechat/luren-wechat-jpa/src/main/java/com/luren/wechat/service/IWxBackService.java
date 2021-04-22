package com.luren.wechat.service;

import com.luren.wechat.entity.WxBack;
import com.luren.wechat.service.dto.WxBackDto;
import com.luren.wechat.service.dto.WxUserDto;

import java.util.List;

/**
 * @author LHH
 */
public interface IWxBackService {
    /**
     *
     * @return ture/false
     */
    Boolean add(WxBackDto wxBackDto);

    /**
     *
     * @param wxUserDto
     */
    List<WxBack> findAllByOpenId(WxUserDto wxUserDto);

    /**
     *
     * @param wxBackDto
     * @return true/false
     */
    Boolean reSet(WxBackDto wxBackDto);
}
