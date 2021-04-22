package com.luren.wechat.service.impl;

import cn.hutool.core.date.DateUtil;
import com.luren.wechat.entity.WxLeave;
import com.luren.wechat.entity.WxUser;
import com.luren.wechat.repository.WxLeaveRepository;
import com.luren.wechat.service.IWxLeaveService;
import com.luren.wechat.service.dto.WxLeaveDto;
import com.luren.wechat.service.dto.WxUserDto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author LHH
 */

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class WxLeaveServiceImpl implements IWxLeaveService {
    @Resource
    private WxLeaveRepository wxLeaveRepository;

    @Resource
    private WxTemperatureServiceImpl wxTemperatureServiceImpl;


    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "WxLeave",key = "#wxLeaveDto.wxUser.openId")
    public Boolean add(WxLeaveDto wxLeaveDto) {
        WxLeave wl = wxLeaveDto.getWxLeave();
        WxUser user = wxLeaveDto.getWxUser();
        wl.setOpenId(user.getOpenId());
        wl.setCreateBy(user.getName());
        Date date = DateUtil.date(System.currentTimeMillis());
        wl.setCreateTime(date);
        wl.setStatus(wxTemperatureServiceImpl.healthStatus(14, user.getOpenId()));
        wl.setCheckStatus(0);
        WxLeave wxLeave = wxLeaveRepository.save(wl);
        return wxLeave.equals(wl);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "WxLeave",key = "#wxUserDto.wxUser.openId",unless = "#result == null")
    public List<WxLeave> findAllByOpenId(WxUserDto wxUserDto) {
        WxUser user = wxUserDto.getWxUser();
        return wxLeaveRepository.findAllByOpenId(user.getOpenId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "WxLeave",key = "#wxLeaveDto.wxUser.openId")
    public Boolean reSet(WxLeaveDto wxLeaveDto) {
        WxLeave wl = wxLeaveDto.getWxLeave();
        try {
            wxLeaveRepository.delete(wl);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
