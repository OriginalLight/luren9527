package com.luren.wechat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luren.wechat.domain.WxBack;
import com.luren.wechat.service.dto.WxBackDto;
import com.luren.wechat.service.dto.WxUserDto;

import java.util.List;

/**
 * @author LHH
 */
public interface IWxBackService extends IService<WxBack>{

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
