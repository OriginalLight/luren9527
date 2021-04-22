package com.luren.wechat.service.impl;

import cn.hutool.core.date.DateUtil;
import com.luren.wechat.entity.WxBack;
import com.luren.wechat.entity.WxUser;
import com.luren.wechat.repository.WxBackRepository;
import com.luren.wechat.service.IWxBackService;
import com.luren.wechat.service.dto.WxBackDto;
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
public class WxBackServiceImpl implements IWxBackService {

    @Resource
    private WxBackRepository wxBackRepository;

    @Resource
    private WxTemperatureServiceImpl wxTemperatureServiceImpl;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "WxBack",key = "#wxBackDto.wxUser.openId")
    public Boolean add(WxBackDto wxBackDto) {
        WxBack wb = wxBackDto.getWxBack();
        WxUser user = wxBackDto.getWxUser();
        wb.setOpenId(user.getOpenId());
        wb.setCreateBy(user.getName());
        Date date = DateUtil.date(System.currentTimeMillis());
        wb.setCreateTime(date);
        wb.setStatus(wxTemperatureServiceImpl.healthStatus(14, user.getOpenId()));
        wb.setCheckStatus(0);
        WxBack wxBack = wxBackRepository.save(wb);
        return wxBack.equals(wb);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "WxBack",key = "#wxUserDto.wxUser.openId",unless = "#result == null")
    public List<WxBack> findAllByOpenId(WxUserDto wxUserDto) {
        WxUser user = wxUserDto.getWxUser();
        return wxBackRepository.findAllByOpenId(user.getOpenId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "WxBack",key = "#wxBackDto.wxUser.openId")
    public Boolean reSet(WxBackDto wxBackDto) {
        WxBack wb = wxBackDto.getWxBack();
        try {
            wxBackRepository.delete(wb);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
