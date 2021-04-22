package com.luren.wechat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luren.wechat.domain.WxLeave;
import com.luren.wechat.service.dto.WxLeaveDto;
import com.luren.wechat.service.dto.WxUserDto;

import java.util.List;

/**
 * @author LHH
 */
public interface IWxLeaveService extends IService<WxLeave>{
    /**
     *
     * @return ture/false
     */
    Boolean add(WxLeaveDto wxLeaveDto);

    /**
     *
     * @param wxUserDto
     */
    List<WxLeave> findAllByOpenId(WxUserDto wxUserDto);

    /**
     *
     * @param wxLeaveDto
     * @return true/false
     */
    Boolean reSet(WxLeaveDto wxLeaveDto);

}
