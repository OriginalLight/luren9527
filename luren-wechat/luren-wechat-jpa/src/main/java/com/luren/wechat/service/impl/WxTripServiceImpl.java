package com.luren.wechat.service.impl;

import cn.hutool.core.date.DateUtil;
import com.luren.wechat.entity.WxTrip;
import com.luren.wechat.entity.WxUser;
import com.luren.wechat.repository.WxTripRepository;
import com.luren.wechat.service.IWxTripService;
import com.luren.wechat.service.dto.WxTripDto;
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
public class WxTripServiceImpl implements IWxTripService {

    @Resource
    private WxTripRepository wxTripRepository;

    @Resource
    private WxTemperatureServiceImpl wxTemperatureServiceImpl;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "WxTrip",key = "#wxTripDto.wxUser.openId")
    public Boolean add(WxTripDto wxTripDto) {
        WxTrip wt = wxTripDto.getWxTrip();
        WxUser user = wxTripDto.getWxUser();
        wt.setOpenId(user.getOpenId());
        wt.setCreateBy(user.getName());
        Date date = DateUtil.date(System.currentTimeMillis());
        wt.setCreateTime(date);
        wt.setStatus(wxTemperatureServiceImpl.healthStatus(14,user.getOpenId()));
        WxTrip wxTrip = wxTripRepository.save(wt);
        return wxTrip.equals(wt);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "WxTrip",key = "#wxUserDto.wxUser.openId",unless = "#result == null")
    public List<WxTrip> findAllByOpenId(WxUserDto wxUserDto) {
        WxUser user = wxUserDto.getWxUser();
        return wxTripRepository.findAllByOpenId(user.getOpenId());
    }
}
